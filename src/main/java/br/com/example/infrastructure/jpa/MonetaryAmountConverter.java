package br.com.example.infrastructure.jpa;

import org.javamoney.moneta.Money;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Objects;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */
@Converter(autoApply = true)
public class MonetaryAmountConverter implements AttributeConverter<MonetaryAmount, BigDecimal> {

    @Override
    public BigDecimal convertToDatabaseColumn(MonetaryAmount attribute) {
        if (Objects.nonNull(attribute)) {
            return attribute.getNumber().numberValue(BigDecimal.class);
        }
        return null;
    }

    @Override
    public MonetaryAmount convertToEntityAttribute(BigDecimal dbData) {
        if (Objects.nonNull(dbData)) {
            return Money.of(dbData, Monetary.getCurrency(Locale.getDefault()));
        }
        return null;
    }
}
