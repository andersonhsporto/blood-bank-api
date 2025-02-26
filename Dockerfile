FROM ubuntu:latest
LABEL authors="anderson"

ENTRYPOINT ["top", "-b"]