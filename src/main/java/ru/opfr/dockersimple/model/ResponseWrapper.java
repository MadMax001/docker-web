package ru.opfr.dockersimple.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class ResponseWrapper {
    public final String version;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    public final LocalDateTime timestamp = LocalDateTime.now();
    public final String ip;
}
