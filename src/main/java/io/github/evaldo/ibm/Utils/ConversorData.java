package io.github.evaldo.ibm.Utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ConversorData {

    public static String formatarDataDdMmYyyy(Date dataOriginal) {
        LocalDateTime dataHora = dataOriginal.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        return dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String formatarLocalDateTimeDataDdMmYyyy(LocalDateTime dataOriginal) {
        return dataOriginal.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
