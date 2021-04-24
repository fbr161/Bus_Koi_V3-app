package com.fbr161.buskoi.ui.purchase_ticket.backend.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fbr161.buskoi.ui.purchase_ticket.backend.model.Bus;
import com.fbr161.buskoi.ui.purchase_ticket.backend.model.IssueTicket;
import com.fbr161.buskoi.ui.purchase_ticket.backend.model.SeatCondition;
import com.fbr161.buskoi.ui.purchase_ticket.backend.repository.Repository_PurchaseTicket;
import com.fbr161.buskoi.ui.purchase_ticket.backend.retrofit.Retrofit_Instanse_PurchaseTicket;

import java.util.HashMap;
import java.util.List;

public class ViewModel_PurchaseTicket extends ViewModel {

    Repository_PurchaseTicket repository_purchaseTicket;
    private MutableLiveData<List<Bus>> busListLiveData = new MutableLiveData<>();
    private MutableLiveData<IssueTicket> issueTicketMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<SeatCondition> seatConditionLiveData = new MutableLiveData<>();

    public ViewModel_PurchaseTicket() {
        //Log.d("wtf__ViewModel_PurchaseTicket", "ViewModel_PurchaseTicket__before__repository_purchaseTicket__initialization");
        repository_purchaseTicket = new Repository_PurchaseTicket();
        issueTicketMutableLiveData.setValue(new IssueTicket());
        //Log.d("wtf__ViewModel_PurchaseTicket", "ViewModel_PurchaseTicket__after__repository_purchaseTicket__initialization");
    }

    public LiveData<List<Bus>> getSearchedBusListLiveData(String frm, String to, String date) {

//        Log.d("wtf__ViewModel_PurchaseTicket", "getSearchedBusListLiveData__before__repository_purchaseTicket.getSearchedBusList");
          busListLiveData = (MutableLiveData<List<Bus>>) repository_purchaseTicket.getSearchedBusList(frm, to, date) ;

//        Log.d("wtf__ViewModel_PurchaseTicket", "getSearchedBusListLiveData__after__repository_purchaseTicket.getSearchedBusList");

        return busListLiveData;
    }

    //fare
    public void setTicketTotalFare(double fare) {
        issueTicketMutableLiveData.getValue().setTotalFare(fare);
    }

    //set Selected Seat Numbers
    public void setSelectedSeatNumbers(String seats) {
        issueTicketMutableLiveData.getValue().setSelectedSeat_no(seats);
    }

    //
    public double getEachTicketFare() {
        return issueTicketMutableLiveData.getValue().getEachTicketFare();
    }

    //get Bus by giving index
    public Bus getBus(int i) {
        //Log.d("wtffffff_ViewModel_PurchaseTicket", "getBus before if, size: "+busListLiveData.getValue().size() );
        if(busListLiveData.getValue().size() > i) {
            //Log.d("wtffffff_ViewModel_PurchaseTicket", "getBus in if: "+busListLiveData.getValue().get(i).getSchedule_id());
            return (Bus) busListLiveData.getValue().get(i);
        }
        return null;
    }
    ////////////////

    //From To Date DayName
    public void setFromToDateDayName(String from, String to, String date, String dayOfWeek){

        IssueTicket it = issueTicketMutableLiveData.getValue();
        it.setFromToDateDayName(from,to, date, dayOfWeek);
        issueTicketMutableLiveData.setValue(it);
    }

    public LiveData<HashMap<String, String>> getFromToDateDayName(){
        HashMap<String, String> value = issueTicketMutableLiveData.getValue().getFromToDateDayName();
        MutableLiveData<HashMap<String, String>> hashMapMutableLiveData = new MutableLiveData<>();
        hashMapMutableLiveData.postValue(value);

        return (LiveData<HashMap<String, String>>) hashMapMutableLiveData;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    MutableLiveData<HashMap<String, String>> selectedSeatNoAndTotalFareLiveData;
    public LiveData<HashMap<String, String>> getSelectedSeatNoAndTotalFare(){
        if(selectedSeatNoAndTotalFareLiveData==null) selectedSeatNoAndTotalFareLiveData = new MutableLiveData<>();
        return selectedSeatNoAndTotalFareLiveData;
    }
    public void setSelectedSeatNoAndTotalFare(){
        HashMap<String, String> hm = issueTicketMutableLiveData.getValue().getSelectedSeatNoAndTotalFare();
        selectedSeatNoAndTotalFareLiveData.postValue(hm);
    }



    //DepTime CompanyName AcStatus Fare SeatNo
    public void setScheduleId_DepTime_CompanyName_AcStatus_Fare_AvailableSeats(String schedule_id, String dep_time, String company_name, boolean ac_status, double fare, int availableSeats){

        selectedSeatNoAndTotalFareLiveData = new MutableLiveData<>();

        IssueTicket it = issueTicketMutableLiveData.getValue();
        it.setScheduleId_DepTime_CompanyName_AcStatus_Fare_AvailableSeats(schedule_id, dep_time, company_name, ac_status, fare, availableSeats);
        issueTicketMutableLiveData.postValue(it);
    }

    public LiveData<HashMap<String, String>> getDepTime_CompanyName_AcStatus_Fare_SeatNo(){

        HashMap<String, String> value = issueTicketMutableLiveData.getValue().getDepTime_CompanyName_AcStatus_Fare_SeatNo();
        MutableLiveData<HashMap<String, String>> hashMapMutableLiveData = new MutableLiveData<>();
        hashMapMutableLiveData.postValue(value);

        return (LiveData<HashMap<String, String>>) hashMapMutableLiveData;
    }

    //user_phn_no Name Phn_no
    public void setUserPhnNo_PassengerName_PassengerPhnNo(String user_phn_no, String passenger_name,  String passenger_phn_no, boolean passenger_gender){

        IssueTicket it = issueTicketMutableLiveData.getValue();
        it.setUserPhnNo_PassengerName_PassengerPhnNo_PassengerGender( user_phn_no,  passenger_name,  passenger_phn_no, passenger_gender);
        issueTicketMutableLiveData.postValue(it);
    }

    public LiveData<HashMap<String, String>> getUserPhnNo_Name_PhnNo(){

        HashMap<String, String> value = issueTicketMutableLiveData.getValue().getUserPhnNo_Name_PhnNo();
        MutableLiveData<HashMap<String, String>> hashMapMutableLiveData = new MutableLiveData<>();
        hashMapMutableLiveData.setValue(value);

        return (LiveData<HashMap<String, String>>) hashMapMutableLiveData;
    }

    //Issued by
    public void setIssued_by(String issued_by) {
        issueTicketMutableLiveData.getValue().setIssued_by(issued_by);
    }



    //Seat condition
    public LiveData<SeatCondition> getSeatConditionLiveData(String schedule_id) {

        seatConditionLiveData = (MutableLiveData<SeatCondition>) repository_purchaseTicket.getSeatCondition(schedule_id);

        return seatConditionLiveData;
    }

    public IssueTicket getIssueTicketMutable() {
        return issueTicketMutableLiveData.getValue();
    }

    public boolean purchaseTicket(String schedule_id, String passenger_phn, String passenger_name, boolean gender, String seat_no, double fare, String issued_by){

        return repository_purchaseTicket.purchaseTicket(schedule_id, passenger_phn, passenger_name, gender, seat_no, fare, issued_by);
    }
}
