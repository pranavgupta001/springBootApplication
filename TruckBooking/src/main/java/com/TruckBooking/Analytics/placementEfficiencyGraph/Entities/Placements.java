package com.TruckBooking.Analytics.placementEfficiencyGraph.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Placements {
    @Id
    @Column(name = "transporter_id")
    private String transporterId;    //transporterId since all the data is corresponding to one transporter.

    private String shipperId;
    private int lastPosition;  // indicates the last index of the below List since while writing data to tha list
                               // we either are updating the last entry or adding a new placement entry.
    // eg. [[id, year, month, week1, placementList], [id, year, month, week2, placementList]]
    // if any of the field year, month, week is different                   |-> lastPosition of List.
    // we have to create new placement info since booking time is different "more in service impl layer"

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "transporter_id")
    private List<PlacementInfo> placementInfoList;

}
