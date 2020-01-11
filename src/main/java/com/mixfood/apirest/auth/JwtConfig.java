package com.mixfood.apirest.auth;

public class JwtConfig
{
    public static final String SECRECT_KEY ="secret.key";

    public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEAv+biBI0pYWqjLK2EIANhZ9Kkzsg1k6dkeB5S1I6w24wC955D\n" +
            "crsxbMSETdenIUd9opsQnnJtHXo47z8CrujV1YbLGqD7wGdD4+/o4hBNWTrt6HeU\n" +
            "qOU4Uhbypd9Ox0Hx3Z8uHNDVqWX9YeL50orbY7NcBq+49FasgVqFoAyS+GF7jG1q\n" +
            "xe71ye9G8fM7dk7Kg4X74fQBZ/tTThwBlQrIfmEx9YDavs1X6KsnEYcZUWxB829S\n" +
            "OhBZERmzpw7MGaVlt8keV2V4FvQiSvaet/wa8s0/5Sr02PevTXKKa526/AsZDIsF\n" +
            "NGN/9zkKDwMb5vOABgL6LCfultn5jDYUPiEhTQIDAQABAoIBABg6O/q7H6hO4D75\n" +
            "wR6RjrCe5XO9zxXPIl4P5gEz6pwtNwqwDm/jtj+3J8T0HwOSlQf8b8KxS5lXHu0F\n" +
            "lX8joiuNEwNPo7kM0INsfIhd6oYDbJnWzwvrtIqYnxXV9YouhyylthqpOvBiEfVT\n" +
            "xJqSMVSLBmlJsh4W+zxxg0FLlRqKbAzfXPxpcnZvfsEzZZwtRCkCZUtj+YwV9htt\n" +
            "MIrl0IfRN8mhcdDXBnmI/ulrQgpgCdPQ5PV8vuhlai/EcaFMg0VND9UWO7DuworQ\n" +
            "l/3jxwHUhnVwZovA6dGKEuF+C4JGdWJAILQqxVC0VLYSRKK+UfbaLB5NfuPAKYib\n" +
            "DyZD5UECgYEA7BNgFZOlj+eXAAgDAHfuq5lURnF0oPdQOd2QObXq2aDPPYnFw38V\n" +
            "WzJBNh4VD1CDJDise+CaOgPNqD74kxLPBWnO2vMNB+EZ/qIw+vNqk1XqrXrHHHm/\n" +
            "ZGW1XQtiIOhyOPwIwWbLq6j/ZFinHp4bsIx2H50U2J8h67MvMMffjOkCgYEA0BkX\n" +
            "6zOyUgYszM1IcnOsiGmmp1ZYPTpqDpFNd8ufDbGE2SrHq3GxM5VS+D1zC79gamdy\n" +
            "jeAfahPohcUS5ly5JqM9HwBoAha2U6E/1DSUWTbrmPndUThQUMUW1EgCRPsX7Tke\n" +
            "HM8oBvCsNNRnKvul+YRv3RTPea478ZtAce6U4sUCgYEA1PqB49VpN1OS+fdAZgrN\n" +
            "KsjQrCCtPI8CNMI73KTPdHlMnlMONNoQ9U54YI9xJnjXZzUt/zBZU1+dCV2OOnls\n" +
            "hZC4ivVdWy9smTnQAIbcLoIDj3DF812vGq9bmM6tP80VR22xpHHN61cy2V/VnZy8\n" +
            "lGGUK982MFwYaSElwNeBSgkCgYEAsajqubut/scU0lhkBIpq/x8rz2sw9Dz9tKgq\n" +
            "eh7WEKvAnmMeimkK/t36MsLLiHj/iHf+3c89IqQSfLoOc6svyyPMF76SDU50pJti\n" +
            "0fl9uVjg9P5FC4VHVF2fKua7QUcZW0bLRY86PgqVCplX5uNnBMojcpzEsL6moJOK\n" +
            "BnrIk30CgYB/3lrl0DZ2dNJTm9ZzN8BY9YKmVLGJMLxt+VProIWrLIdY9PygNmWH\n" +
            "k5dN3ixyYXWO3RvRGMmrFYmcS9SjObgeebR09vPaSG894NSKsRDGj2AvLBrVENHk\n" +
            "AFz9+QCZWm8/DtKbvfomDO/Z5rTV4FTGb/KY4rVYOe7rQAoE4ORksQ==\n" +
            "-----END RSA PRIVATE KEY-----" ;

    public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv+biBI0pYWqjLK2EIANh\n" +
            "Z9Kkzsg1k6dkeB5S1I6w24wC955DcrsxbMSETdenIUd9opsQnnJtHXo47z8CrujV\n" +
            "1YbLGqD7wGdD4+/o4hBNWTrt6HeUqOU4Uhbypd9Ox0Hx3Z8uHNDVqWX9YeL50orb\n" +
            "Y7NcBq+49FasgVqFoAyS+GF7jG1qxe71ye9G8fM7dk7Kg4X74fQBZ/tTThwBlQrI\n" +
            "fmEx9YDavs1X6KsnEYcZUWxB829SOhBZERmzpw7MGaVlt8keV2V4FvQiSvaet/wa\n" +
            "8s0/5Sr02PevTXKKa526/AsZDIsFNGN/9zkKDwMb5vOABgL6LCfultn5jDYUPiEh\n" +
            "TQIDAQAB\n" +
            "-----END PUBLIC KEY-----";
}
