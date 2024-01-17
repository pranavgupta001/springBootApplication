package com.TruckBooking.Analytics.placementEfficiencyGraph.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//
@Entity
@Data
@NoArgsConstructor
public class PlacementInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int year;   //2023
    private int month;  // 12
    private int week;   // 3

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> placementList; //Stores bookings according to time taken to assign vehicle [20,30,20,20,10]
                                         // if delivery between 0-5 hrs than index=0,5-10 hrs indx=1, 10-15 hrs indx=2 n so on upto indx=4
                                         // indx 4 for time >20hrs
    public PlacementInfo(int year, int month, int week, List<Integer> placementList){
        this.year = year;
        this.month = month;
        this.week = week;
        this.placementList = placementList;
    }
}
