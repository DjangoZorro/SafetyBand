package the.challenge.safetyband.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import the.challenge.safetyband.domain.ConfirmationToken;
import the.challenge.safetyband.domain.WebAdmin;
import the.challenge.safetyband.repositories.WebAdminRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.text.MessageFormat;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WebAdminService implements UserDetailsService {
    private final WebAdminRepository webAdminRepository;
    private ConfirmationTokenService confirmationTokenService;
    private EmailService emailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private EmailService mailer;

    void sendConfirmationMail(String webAdminMail, String token) {
        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(webAdminMail);
        mailMessage.setSubject("SafetyBand - Verificatie E-Mail!");
        mailMessage.setFrom("newneonretro@gmail.com");
        mailMessage.setText("Bedankt voor het registreren. Klik op de link hieronder om uw account te activeren. " + "http://localhost:8080/sign-up/confirm?token=" + token);
        emailService.sendEmail(mailMessage);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<WebAdmin> optionalUser = webAdminRepository.findByEmail(email);

        return optionalUser.orElseThrow(() -> new UsernameNotFoundException(MessageFormat.format("Gebruiker met email {0} kan niet gevonden worden.", email)));
    }

    public void signUpWebAdmin(WebAdmin webAdmin) {

        final String encryptedPassword = bCryptPasswordEncoder.encode(webAdmin.getWachtwoord());

        webAdmin.setWachtwoord(encryptedPassword);

        final WebAdmin createdWebAdmin = webAdminRepository.save(webAdmin);

        final ConfirmationToken confirmationToken = new ConfirmationToken(webAdmin);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        sendConfirmationMail(webAdmin.getEmail(), confirmationToken.getConfirmationToken());
    }

    public void confirmWebAdmin(ConfirmationToken confirmationToken) {
        final WebAdmin webAdmin = confirmationToken.getWebAdmin();
        webAdmin.setEnabled(true);
        webAdminRepository.save(webAdmin);
        confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());
    }
}
