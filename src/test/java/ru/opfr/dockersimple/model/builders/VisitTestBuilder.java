package ru.opfr.dockersimple.model.builders;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import ru.opfr.dockersimple.model.Visit;

@AllArgsConstructor
@NoArgsConstructor(staticName = "aVisit")
@With
public class VisitTestBuilder implements TestBuilder<Visit>{
    private String ips = "1.1.1.1";
    @Override
    public Visit build() {
        Visit visit = new Visit();
        visit.setIps(ips);
        return visit;
    }
}
