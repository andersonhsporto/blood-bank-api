package dev.api.bloodbankapi.base;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.NativeWebRequest;

@NoArgsConstructor
public class ApiUtil {

  public static void setExampleResponse(NativeWebRequest req, String contentType, String example)
      throws RuntimeException {
    try {
      HttpServletResponse res = (HttpServletResponse) req.getNativeResponse(
          HttpServletResponse.class);

      res.setCharacterEncoding("UTF-8");
      res.addHeader("Content-Type", contentType);
      res.getWriter().print(example);
    } catch (IOException var4) {
      throw new RuntimeException(var4);
    }
  }

}
