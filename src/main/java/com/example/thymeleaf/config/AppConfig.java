package com.example.thymeleaf.config;

import com.example.thymeleaf.entity.ContactMessage;
import com.example.thymeleaf.service.MessageService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Getter
@Setter
@Configuration
public class AppConfig {

    @Value("${spring.application.display-name}")
    private String appDisplayName;

    @Value("${spring.http.multipart.upload-path}")
    private String uploadPath;

    @Value("${spring.servlet.multipart.max-file-size}")
    private String uploadSizeLimit;

    @Autowired
    private MessageService messageService;

    //Seed data
    @PostConstruct
    public void loadSeedData() {
        //Seed data from https://www.mockaroo.com/
        var seedData = messageService.addAllMessages(List.of(
                new ContactMessage(1L, "apellew0@cocolog-nifty.com", "Duis mattis egestas metus. Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh."),
                new ContactMessage(2L, "ndove1@topsy.com", "Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus. Pellentesque eget nunc."),
                new ContactMessage(3L, "oclout2@dedecms.com", "Vivamus vel nulla eget eros elementum pellentesque. Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus. Phasellus in felis."),
                new ContactMessage(4L, "dmelding3@dmoz.org", "Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus. Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero."),
                new ContactMessage(5L, "tteek4@time.com", "Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat."),
                new ContactMessage(6L, "imarler5@domainmarket.com", "Maecenas pulvinar lobortis est. Phasellus sit amet erat. Nulla tempus."),
                new ContactMessage(7L, "thonywill6@joomla.org", "Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est. Phasellus sit amet erat. Nulla tempus."),
                new ContactMessage(8L, "mbelvin7@prlog.org", "Etiam pretium iaculis justo. In hac habitasse platea dictumst. Etiam faucibus cursus urna."),
                new ContactMessage(9L, "nvignaux8@abc.net.au", "Ut at dolor quis odio consequat varius. Ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi."),
                new ContactMessage(10L, "jhandling9@gov.uk", "Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis."),
                new ContactMessage(11L, "egerltsa@wiley.com", "Etiam justo. Etiam pretium iaculis justo. In hac habitasse platea dictumst."),
                new ContactMessage(12L, "cpullenb@engadget.com", "Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat."),
                new ContactMessage(13L, "sleavyc@ucoz.ru", "Fusce consequat. Nulla nisl. Nunc nisl."),
                new ContactMessage(14L, "lcarousd@eepurl.com", "ac neque. Duis bibendum. Morbi non quam nec dui luctus rutrum."),
                new ContactMessage(15L, "adoe@dmoz.org", "Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis.")
        ));
    }
}
