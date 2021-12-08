package co.edu.uniquindio.unishop.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mail {
    private String username;
    private String to;
    private String subject;
    private String text;
    private File file;
    private Date sendDate;
}
