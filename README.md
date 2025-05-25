<!-- Cabeçalho -->

# Validador de e-mails

___

#### Ferramentas Utilizadas

![Java](https://skillicons.dev/icons?i=java)

___

<!-- Corpo do README -->
## Descrição
O `EmailValidator` é uma classe Java simples, porém robusta, projetada para validar endereços de e-mail. Ele utiliza expressões regulares (Regex) para verificar a conformidade do e-mail com os padrões comuns, além de realizar checagens básicas como nulidade, string vazia e comprimento máximo.
Este projeto foi desenvolvido com fins educacionais, servindo como um exemplo prático de validação de dados em Java e o uso de Regex. Ele é configurável, permitindo que o usuário defina sua própria expressão regular e limite de comprimento, se necessário.

___

## Funcionalidades

-   **Verificação de Nulidade:** Garante que o e-mail fornecido não seja `null`.
-   **Verificação de String Vazia:** Impede que e-mails vazios ou contendo apenas espaços sejam considerados válidos.
-   **Validação de Comprimento Máximo:** Checa se o e-mail excede um comprimento máximo pré-definido (padrão: 254 caracteres), que pode ser personalizado.
-   **Validação por Expressão Regular (Regex):**
    -   Utiliza uma Regex padrão abrangente para validar a estrutura do e-mail.
    -   Verifica a parte local (antes do `@`) permitindo caracteres alfanuméricos e `._%+-`.
    -   Valida o domínio, permitindo caracteres alfanuméricos e hífens. Importante: segmentos do domínio (ex: `meu-dominio`) não podem começar nem terminar com hífen.
    -   Suporta subdomínios (ex: `email@sub.dominio.com`).
    -   O Top-Level Domain (TLD, ex: `.com`, `.org`) deve ser alfabético e ter entre 1 e 6 caracteres.
-   **Configurabilidade:**
    -   Permite a instanciação com uma Regex e um comprimento máximo personalizados através de um construtor alternativo.
    -   As mensagens de retorno para "email válido" e "email inválido" também podem ser personalizadas.
-   **Retorno Booleano:** O método de validação principal (`validateEmail`) retorna `true` se o e-mail for válido e `false` caso contrário, facilitando a integração lógica.

___

## Como Usar

Para utilizar o validador em seu projeto Java:

1.  Inclua a classe `EmailValidator.java` no seu projeto.
2.  Crie uma instância da classe e chame o método `validateEmail(String email)`.

```java
public class ExemploUso {
    public static void main(String[] args) {
        EmailValidator validator = new EmailValidator();

        String emailValido = "contato@exemplo.com";
        String emailInvalido = "usuario@dominio-com-hifen-no-fim-.com";
        String emailNulo = null;

        System.out.println("\"" + emailValido + "\" é: " +
                (validator.validateEmail(emailValido) ? validator.getValidEmailMessage() : validator.getInvalidEmailMessage()));

        System.out.println("\"" + emailInvalido + "\" é: " +
                (validator.validateEmail(emailInvalido) ? validator.getValidEmailMessage() : validator.getInvalidEmailMessage()));

        System.out.println("E-mail nulo é: " +
                (validator.validateEmail(emailNulo) ? validator.getValidEmailMessage() : validator.getInvalidEmailMessage()));

        // Exemplo com validador personalizado (regex, max_length, msg_valido, msg_invalido)
        // EmailValidator customValidator = new EmailValidator("^[a-z]+@[a-z]+\\.[a-z]{2,3}$", 50, "OK", "Falhou");
        // System.out.println("\"teste@site.co\" (custom): " +
        //         (customValidator.validateEmail("teste@site.co") ? customValidator.getValidEmailMessage() : customValidator.getInvalidEmailMessage()));
    }
}
```

___

## Testes

A classe `EmailValidator.java` inclui um método `main` que serve como um conjunto de testes básicos, demonstrando a validação para diversos cenários de e-mails. Para executá-lo:

```bash
javac EmailValidator.java
java EmailValidator
```
Isso imprimirá no console os resultados da validação para cada e-mail de teste definido no método `main`.

___
<!-- Contato -->
### Minhas redes de contato:

[![Instagram](https://skillicons.dev/icons?i=instagram)](https://www.instagram.com/back.pech/)
[![Discord](https://skillicons.dev/icons?i=discord)](https://discord.gg/b3zP3ArVJk)
[![E-mail](https://skillicons.dev/icons?i=gmail)](mailto:backpech.ctt@gmail.com)
[![Linkedin](https://skillicons.dev/icons?i=linkedin)](https://www.linkedin.com/in/backpech)
[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://buymeacoffee.com/moonrilo)
<!-- Contato -->
