package org.sid.billingservice.entities;

import org.springframework.data.rest.core.config.Projection;
import java.util.Date;

@Projection(name = "fullBillProjection",types = Bill.class)
public interface BillProjection {
    public long getId();
    public long getCustomerId();
    public Date getBillDate();
}
