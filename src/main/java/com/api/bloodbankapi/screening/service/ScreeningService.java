package com.api.bloodbankapi.screening.service;

import com.api.bloodbankapi.commons.enums.ScreeningStatus;
import com.api.bloodbankapi.commons.exception.DonorAlreadyScreenedCustomException;
import com.api.bloodbankapi.commons.exception.DonorNotFoundCustomException;
import com.api.bloodbankapi.commons.exception.ScreeningNotFoundCustomException;
import com.api.bloodbankapi.donor.entity.Donor;
import com.api.bloodbankapi.donor.repository.DonorRepository;
import com.api.bloodbankapi.question.entity.Question;
import com.api.bloodbankapi.question.repository.QuestionRepository;
import com.api.bloodbankapi.screening.dto.ScreeningQuestionDTO;
import com.api.bloodbankapi.screening.entity.ScreeningQuestion;
import com.api.bloodbankapi.screening.repository.ScreeningPagingRepository;
import com.api.bloodbankapi.screening.repository.ScreeningRepository;
import com.api.bloodbankapi.screening.dto.ProtocolDTO;
import com.api.bloodbankapi.screening.dto.ScreeningDTO;
import com.api.bloodbankapi.screening.entity.Screening;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
public class ScreeningService {

    private final ScreeningRepository screeningRepository;

    private final ScreeningPagingRepository screeningPagingRepository;

    private final DonorRepository donorRepository;

    private final QuestionRepository questionRepository;

    public ProtocolDTO createScreening(String donorDocumentId) {
        var donorEntity = donorRepository.findByDocumentId(donorDocumentId)
                .orElseThrow(() -> new DonorNotFoundCustomException("Donor not found with documentId: " + donorDocumentId));

        if (donorAlreadyScreenedToday(donorEntity)) {
            log.info("Donor already screened today: {}", donorDocumentId);
            throw new DonorAlreadyScreenedCustomException();
        }

        var screeningEntity = generateScreening(donorEntity);

        screeningRepository.save(screeningEntity);
        log.info("Screening created: {}", screeningEntity.getProtocol());

        return new ProtocolDTO(screeningEntity.getProtocol(), screeningEntity.getDate(), screeningEntity.getStatus(), donorEntity.getObservation());
    }

    public ScreeningDTO findScreeningByProtocol(String protocol) {
        var screeningEntity = screeningRepository.findByProtocol(protocol)
                .orElseThrow(() -> new ScreeningNotFoundCustomException("Screening not found with protocol: " + protocol));

        return new ScreeningDTO(
                screeningEntity.getId().toString(),
                screeningEntity.getDonor().getName(),
                screeningEntity.getProtocol(),
                screeningEntity.getDate(),
                screeningEntity.getStatus().name(),
                screeningEntity.getDonor().getObservation(),
                false
        );
    }

    public List<ScreeningDTO> findActiveScreenings() {
        return screeningRepository
                .findByStatus(ScreeningStatus.PENDING)
                .stream()
                .map(screeningEntity -> new ScreeningDTO(
                        screeningEntity.getId().toString(),
                        screeningEntity.getDonor().getName(),
                        screeningEntity.getProtocol(),
                        screeningEntity.getDate(),
                        screeningEntity.getStatus().name(),
                        screeningEntity.getDonor().getObservation(),
                        false
                ))
                .collect(Collectors.toList());
    }

    public List<ScreeningDTO> findInactiveScreenings() {
        return screeningRepository.findByStatusNotContaining(ScreeningStatus.PENDING).stream()
                .map(screeningEntity -> new ScreeningDTO(
                        screeningEntity.getId().toString(),
                        screeningEntity.getDonor().getName(),
                        screeningEntity.getProtocol(),
                        screeningEntity.getDate(),
                        screeningEntity.getStatus().name(),
                        screeningEntity.getDonor().getObservation(),
                        false
                ))
                .collect(Collectors.toList());
    }

    public List<ScreeningDTO> findPage(int page, int size) {
        Page<Screening> screeningPage = screeningPagingRepository.findAll(PageRequest.of(page, size, Sort.by("date")));

        return screeningPage.stream()
                .map(screeningEntity -> new ScreeningDTO(
                        screeningEntity.getId().toString(),
                        screeningEntity.getDonor().getName(),
                        screeningEntity.getProtocol(),
                        screeningEntity.getDate(),
                        screeningEntity.getStatus().name(),
                        screeningEntity.getDonor().getObservation(),
                        false
                ))
                .collect(Collectors.toList());
    }

    private Screening generateScreening(Donor donorEntity) {
        String protocol = protocolGenerator(donorEntity.getName(), donorEntity.getDocumentId());
        Screening screeningEntity = Screening.builder()
                .protocol(protocol)
                .donor(donorEntity)
                .date(LocalDateTime.now())
                .status(ScreeningStatus.PENDING)
                .build();
        List<ScreeningQuestion> screeningQuestions = createScreeningQuestions(screeningEntity);

        screeningEntity.setQuestions(screeningQuestions);
        return screeningEntity;
    }

    private List<ScreeningQuestion> createScreeningQuestions(Screening screeningEntity) {
        List<Question> questions = findActiveQuestions();

        return questions.stream()
                .map(question -> ScreeningQuestion.builder()
                        .question(question)
                        .screening(screeningEntity)
                        .isAnswered(false)
                        .build())
                .collect(Collectors.toList());
    }

    private List<Question> findActiveQuestions() {
        return questionRepository.findByActiveTrue();
    }

    private boolean donorAlreadyScreenedToday(Donor donorEntity) {
        LocalDateTime now = LocalDateTime.now();
        return donorEntity.getScreenings().stream()
                .anyMatch(screening -> screening.getDate().getDayOfYear() == now.getDayOfYear());
    }

    private String protocolGenerator(String name, String documentId) {
        LocalDateTime now = LocalDateTime.now();
        return String.format("%s%s%02d%02d%04d%02d%02d%02d",
                name.substring(0, 3).toUpperCase(),
                documentId.toUpperCase(),
                now.getDayOfMonth(),
                now.getMonthValue(),
                now.getYear(),
                now.getHour(),
                now.getMinute(),
                now.getSecond());
    }

    public List<ScreeningQuestionDTO> findScreeningQuestions(String protocol) {
        var screeningEntity = screeningRepository.findByProtocol(protocol)
                .orElseThrow(() -> new ScreeningNotFoundCustomException("Screening not found with protocol: " + protocol));

        Map<String, String> questionsAndAnswers = screeningEntity.getQuestions().stream()
                .collect(Collectors.toMap(
                        screeningQuestion -> screeningQuestion.getQuestion().getQuestionText(),
                        screeningQuestion -> Optional.ofNullable(screeningQuestion.getAnswer()).orElse("")
                ));

        boolean answeredAllQuestions = screeningEntity.getQuestions().stream()
                .allMatch(ScreeningQuestion::getIsAnswered);

        return questionsAndAnswers.entrySet().stream()
                .map(entry -> new ScreeningQuestionDTO(questionsAndAnswers, answeredAllQuestions))
                .toList();
    }
}

