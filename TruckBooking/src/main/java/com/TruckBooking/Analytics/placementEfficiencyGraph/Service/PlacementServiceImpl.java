package com.TruckBooking.Analytics.placementEfficiencyGraph.Service;

import com.TruckBooking.Analytics.placementEfficiencyGraph.Dao.PlacementDao;
import com.TruckBooking.Analytics.placementEfficiencyGraph.Entities.PlacementInfo;
import com.TruckBooking.Analytics.placementEfficiencyGraph.Entities.Placements;
import com.TruckBooking.Booking.Dao.BookingDao;
import com.TruckBooking.Booking.Entities.BookingData;
import com.TruckBooking.ContractRateUpload.Dao.ContractRateRepo;
import com.TruckBooking.ContractRateUpload.Dao.IndentDao;
import com.TruckBooking.ContractRateUpload.Entity.Indent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Service
public class PlacementServiceImpl implements PlacementService{

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private PlacementDao placementDao;

    @Autowired
    private IndentDao indentDao;

    @Autowired
    private ContractRateRepo contractRateRepo;

    private static Timestamp lastSchedulerTimestamp = Timestamp.from(Instant.now());
    //storing the last timestamp at which scheduler ran

    @Override
    public Object getPlacementData(String id){
        if (id.charAt(0) == 't'){ //checking if id given is transporterId or shipperId  since ids begin with the respective id name
            // transporterId = "transporter: heX codE"
            Optional<Placements> optional = placementDao.findById(id);
            if (optional.isPresent()){
                Placements responsePlacement = optional.get();
                responsePlacement.setPlacementInfoList(List.of(responsePlacement.getPlacementInfoList().get(0)));
                return responsePlacement;
            }
            else return "TransporterId does not exists.";
        }
        else{
            Optional<List<Placements>> optional = placementDao.findByShipperId(id);
            if (optional.isPresent()){
                List<Placements> list = optional.get();
                for (Placements x: list){
                    // since there are duplicate entries therefore to get any entry we get the corresponding one
                    x.setPlacementInfoList(List.of(x.getPlacementInfoList().get(0)));
                }
                return list;
            }
            else return "ShipperId does not exists";
        }
    }

    public void createNewPlacement(Placements placements, List<PlacementInfo> placementInfoList, int bookingYear, int bookingMonth, int bookingWeek, int index){
        // Method for creating a new Placement since all the data types are primitive therefore this method is very helpful
        //placementList for adding in PlacementInfo
        List<Integer> placementList = new ArrayList<>();
        placementList.add(0);
        placementList.add(0);
        placementList.add(0);
        placementList.add(0);
        placementList.add(0);
        placementList.set(index,1);

        PlacementInfo newPlacementInfo = new PlacementInfo(bookingYear,bookingMonth,bookingWeek,placementList);
        placementInfoList.add(newPlacementInfo);
        placements.setPlacementInfoList(placementInfoList);

    }

    @Scheduled(fixedDelay = 60000)
    public void FindPlacement(){
        //Scheduler for calculating the placement info
//        calculate the bookings remaining after the previous timeStamp
        List<BookingData> bookingDataList = bookingDao.findByTimestampIsBefore(lastSchedulerTimestamp);
        lastSchedulerTimestamp = Timestamp.from(Instant.now());

        if (!bookingDataList.isEmpty()){
            System.out.println("Bookings are Found");

            List<PlacementInfo> placementInfoList;
            List<Integer> placementList;
            PlacementInfo placementInfo;

            for (BookingData bookingData: bookingDataList){
                // getting transporterId from booking Data
                // shipperId from contractService through transporterId
                // these ids will be stored.
                String transporterId = bookingData.getTransporterId();
                String shipperId = contractRateRepo.findByTransporterId(transporterId).getShipperId();

                String loadId = bookingData.getLoadId(); // to get the data from indent table load Id is reqd.
                Timestamp bookingTimestamp = bookingData.getTimestamp();
                long bookingMillis = bookingTimestamp.getTime(); // getting bookingMillis to make booking calendar and find the year, month, week of booking
                Calendar bookingCalendar = Calendar.getInstance();
                bookingCalendar.setTimeInMillis(bookingMillis);
                int bookingYear = bookingCalendar.get(Calendar.YEAR);
                int bookingMonth = bookingCalendar.get(Calendar.MONTH);
                int bookingWeek = bookingCalendar.get(Calendar.WEEK_OF_MONTH);

                Indent indent = indentDao.findByLoadId(loadId);

                Timestamp indentTimestamp;
                indentTimestamp = indent.getAssignedTime();
                long  indentMillis = indentTimestamp.getTime(); // getting indentMillis to calculate the time difference between
                                                            // load assigning to the transporter and transporter assigning the vehicle for load
                int millisInAnHour = 3600000;  // millis in 1 hour

                int index ; // index for the list at which we will add the booking
                long difference = bookingMillis-indentMillis;
                if (difference <= 5*millisInAnHour){  // 0-5 hours
                    index=0;
                }
                else if (difference <= 10*millisInAnHour){// 5-10 hours
                    index=1;
                }
                else if (difference <= 15*millisInAnHour){  // 10-15 hours
                    index=2;
                }
                else if (difference <= 20*millisInAnHour){  // 15-20 hrs
                    index=3;
                }
                else{
                    index=4;    // >20 hours
                }

                Optional<Placements> optional= placementDao.findById(transporterId); // finding if the placement for this transporter existed before or not
                Placements placements;
                if (optional.isPresent()){

                    placements = optional.get();
                    int at = placements.getLastPosition();
                    placementInfoList = placements.getPlacementInfoList();
                    placementInfo = placementInfoList.get(at);            // Info for the last placement
                    int lastPlacementYear = placementInfo.getYear();
                    int lastPlacementMonth = placementInfo.getMonth();
                    int lastPlacementWeek = placementInfo.getWeek();
                    // getting time of last accepted booking week to compare for the current booing week

                    if (lastPlacementYear == bookingYear){
                        if (lastPlacementMonth == bookingMonth){
                            if (lastPlacementWeek == bookingWeek){
                                placementList = placementInfo.getPlacementList();
                                placementList.set(index,placementList.get(index)+1);
                                placementInfo.setPlacementList(placementList);
                            }
                            else{
                                createNewPlacement(placements,placementInfoList,bookingYear,bookingMonth,bookingWeek,index);
                            }
                        }
                        else{
                            createNewPlacement(placements,placementInfoList,bookingYear,bookingMonth,bookingWeek,index);
                        }
                    }
                    else{
                        createNewPlacement(placements,placementInfoList,bookingYear,bookingMonth,bookingWeek,index);
                    }
                }
                else{
                    placements = new Placements();
                    placementInfoList = new ArrayList<>();

                    createNewPlacement(placements, placementInfoList, bookingYear, bookingMonth, bookingWeek, index);

                    placements.setTransporterId(transporterId);
                    placements.setShipperId(shipperId);
                    placements.setLastPosition(0);
                }
                placementDao.save(placements);
            }
        }
    }
}
