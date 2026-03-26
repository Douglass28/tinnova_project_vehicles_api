package tinnova.test.com.example.demo.domain.entities.validators;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import java.util.Objects;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.function.Function;
import tinnova.test.com.example.demo.domain.entities.vehicle.Vehicle;
import br.com.fluentvalidator.AbstractValidator;

public class VehicleValidator extends AbstractValidator<Vehicle> {

    @Override
    public void rules() {

        ruleFor(Vehicle::getId)
                .must(Objects::nonNull)
                .withMessage("O campo id não pode ser nulo.");
        ruleFor(Vehicle::getChassis)
                .must(not(nullValue()))
                .withMessage("O campo chassis não pode ser nulo ou vazio.");
        ruleFor(Vehicle::getPlate)
                .must(not(nullValue()))
                .withMessage("O campo plate não pode ser nulo ou vazio.");
        ruleFor(Vehicle::getBrand)
                .must(not(nullValue()))
                .withMessage("O campo brand não pode ser nulo ou vazio.");
        ruleFor(Vehicle::getModel)
                .must(not(nullValue()))
                .withMessage("O campo model não pode ser nulo ou vazio.");
        ruleFor(Vehicle::getYear)
                .must(not(nullValue()))
                .withMessage("O campo year não pode ser nulo.");
        ruleFor(Vehicle::getColor)
                .must(not(nullValue()))
                .withMessage("O campo color não pode ser nulo ou vazio.");
        ruleFor(Vehicle::getPrice)
                .must(not(nullValue()))
                .withMessage("O campo price não pode ser nulo.");
        ruleFor(Vehicle::getType)
                .must(not(nullValue()))
                .withMessage("O campo type não pode ser nulo.");
        ruleFor(Vehicle::getCreatedAt)
                .must(not(nullValue()))
                .withMessage("O campo createdAt não pode ser nulo.");
        ruleFor(Vehicle::getUpdatedAt)
                .must(not(nullValue()))
                .withMessage("O campo updatedAt não pode ser nulo.");
        ruleFor(Vehicle::getPrice)
                .must(price -> price != null && price.compareTo(BigDecimal.ZERO) > 0)
                .withMessage("O campo price deve ser maior que zero.");
        ruleFor(Vehicle::getYear)
                .must(year -> year != null && year >= 1886 && year <= LocalDateTime.now().getYear() + 1)
                .withMessage("O campo year está fora do intervalo permitido.");
        ruleFor(Function.identity())
                .must(vehicle -> vehicle.getCreatedAt() == null
                        || vehicle.getUpdatedAt() == null
                        || !vehicle.getUpdatedAt().isBefore(vehicle.getCreatedAt()))
                .withMessage("updatedAt não pode ser anterior ao createdAt.");
    }
}
