import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EmailValidator {

    // Regex para validar e-mails.
    // Permite:
    // - Parte local: alfanuméricos, '.', '_', '%', '+', '-'
    // - Domínio: alfanuméricos, '-', cada segmento não pode começar ou terminar com hífen.
    // - Subdomínios: separados por '.'
    // - TLD: alfabético de 1 a 6 caracteres.
    // - Não permite espaços em branco internos.
    // - Não permite pontos consecutivos no domínio (ex: example..com)
    // - Não permite que um segmento do domínio comece ou termine com hífen.

    private final String emailRegex;
    private final Pattern emailPattern;
    private final String validEmailMessage;
    private final String invalidEmailMessage;
    private final int maxEmailLength;

    // Construtor padrão com as regras atuais
    public EmailValidator() {
        // Regex atualizada para garantir que segmentos do domínio não comecem/terminem com hífen:
        // Um segmento de domínio é [a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?
        // O domínio completo é um ou mais desses segmentos, separados por pontos.
        this.emailRegex =
            "^[a-zA-Z0-9._%+-]+@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?)(?:\\.(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?))*\\.[a-zA-Z]{1,6}$";
        this.emailPattern = Pattern.compile(this.emailRegex);
        this.validEmailMessage = "email válido";
        this.invalidEmailMessage = "email inválido";
        this.maxEmailLength = 254;
    }

    //Construtor para personalizar as regras:
    public EmailValidator(String regex, String validMsg, String invalidMsg, int maxLength) {
    this.emailRegex = regex;
    this.emailPattern = Pattern.compile(this.emailRegex);
    this.validEmailMessage = validMsg;
    this.invalidEmailMessage = invalidMsg;
    this.maxEmailLength = maxLength;
}

    public String getValidEmailMessage() {
        return validEmailMessage;
    }

    public String getInvalidEmailMessage() {
        return invalidEmailMessage;
    }

    public boolean validateEmail(String email) {
        // 1. Checagem de nulidade
        if (email == null) {
            return false;
        }

        // 2. Checagem de string vazia ou apenas espaços em branco
        // trim() remove espaços no início e fim. Se sobrar vazio, é inválido.
        if (email.trim().isEmpty()) {
            return false;
        }

        // 3. Checagem de comprimento máximo
        if (email.length() > this.maxEmailLength) {
            return false;
        }

        // 4. Validação principal usando a regex compilada
        // A regex já lida com:
        // - Estrutura geral (local@dominio.tld)
        // - Caracteres permitidos em cada parte
        // - Ausência de espaços internos (devido aos conjuntos de caracteres e âncoras ^$)
        // - Pontos consecutivos no domínio
        // - TLD com comprimento adequado
        // - Espaços no início ou fim (a regex com ^ e $ não os permitirá)
        
        Matcher matcher = this.emailPattern.matcher(email);
        return matcher.matches();
    }

    public static void main(String[] args) {
        // Criar uma instância do validador
        EmailValidator validator = new EmailValidator();

        // Função auxiliar para imprimir resultados
        java.util.function.Consumer<String> testEmail = (email) -> {
            boolean isValid = validator.validateEmail(email);
            String message = isValid ? validator.getValidEmailMessage() : validator.getInvalidEmailMessage();
            System.out.println("\"" + email + "\" é: " + message);
        };

        // Exemplos de uso
        testEmail.accept("email@example.com");
        testEmail.accept("firstname.lastname@example.com");
        testEmail.accept("email@subdomain.example.com");
        testEmail.accept("email@example.co.uk");
        testEmail.accept("email@example"); // Inválido (sem TLD claro pela regex)
        testEmail.accept("email@example.com.com.br");
        testEmail.accept("email-example@example.com");
        testEmail.accept("email@example..com"); // Inválido (pontos consecutivos no domínio)
        testEmail.accept("email@.com"); // Inválido (domínio começa com ponto)
        testEmail.accept("@example.com"); // Inválido (sem parte local)
        testEmail.accept("email_example@example.com");
        testEmail.accept("example@example.com.a"); // Válido (TLD 'a' com 1 char)
        testEmail.accept("example@example.com.abcdefg"); // Inválido (TLD > 6 chars)
        testEmail.accept("email@domain-with-hyphen.com");
        testEmail.accept("email@-startshyphen.com"); // Deve ser inválido com a nova regex
        testEmail.accept("email@endshyphen-.com");   // Deve ser inválido com a nova regex
        testEmail.accept("email@sub.-domain.com"); // Deve ser inválido com a nova regex

        // Casos de teste adicionais para as condições simplificadas/removidas
        System.out.println("null é: " + (validator.validateEmail(null) ? validator.getValidEmailMessage() : validator.getInvalidEmailMessage()));
        testEmail.accept("");
        testEmail.accept("   ");
        String longEmail = "a".repeat(250) + "@example.com"; // 250 (local) + 1 (@) + 7 (domain) + 3 (.com) = 261 > 254
        System.out.println("Email muito longo (" + longEmail.length() + " caracteres) é: " + (validator.validateEmail(longEmail) ? validator.getValidEmailMessage() : validator.getInvalidEmailMessage()));
        String longEmailOk = "a".repeat(240) + "@example.com"; // 240 + 1 + 7 + 3 = 251 <= 254
        System.out.println("Email com comprimento ok (" + longEmailOk.length() + " caracteres) é: " + (validator.validateEmail(longEmailOk) ? validator.getValidEmailMessage() : validator.getInvalidEmailMessage()));
        testEmail.accept("email @example.com"); // Inválido (espaço interno)
        testEmail.accept(" email@example.com "); // Inválido (espaços nas bordas, mas trim() antes da regex lida com isso)
    }
}