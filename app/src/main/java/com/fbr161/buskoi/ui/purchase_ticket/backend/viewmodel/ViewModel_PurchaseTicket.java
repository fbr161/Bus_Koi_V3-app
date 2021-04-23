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

    //get Bus by giving index
    public Bus getBus(int i) {
        Log.d("wtffffff_ViewModel_PurchaseTicket", "getBus before if, size: "+busListLiveData.getValue().size() );
        if(busListLiveData.getValue().size() > i) {
            Log.d("wtffffff_ViewModel_PurchaseTicket", "getBus in if: "+busListLiveData.getValue().get(i).getSchedule_id());
            return (Bus) busListLiveData.getValue().get(i);
        }
        return null;
    }
    ////////////////
    public void setSelected_bus_schedule_id(String schedule_id) {
        issueTicketMutableLiveData.getValue().setSchedule_id(schedule_id);
    }

    //From To Date DayName
    public void setFromToDateDayName(String from, String to, String date, String dayOfWeek){

        //Log.d("wtffffff_ViewModel_PurchaseTicket", "setFromToDateDayName before init");
        IssueTicket it = issueTicketMutableLiveData.getValue();
        //Log.d("wtffffff_ViewModel_PurchaseTicket", "setFromToDateDayName before init 1");
        it.setFromToDateDayName(from,to, date, dayOfWeek);
        //Log.d("wtffffff_ViewModel_PurchaseTicket", "setFromToDateDayName before init 2");
        issueTicketMutableLiveData.setValue(it);
        //Log.d("wtffffff_ViewModel_PurchaseTicket", "setFromToDateDayName after init");
    }

    public LiveData<HashMap<String, String>> getFromToDateDayName(){
        //Log.d("wtffffff_ViewModel_PurchaseTicket", "getFromToDateDayName before value init");
        HashMap<String, String> value = issueTicketMutableLiveData.getValue().getFromToDateDayName();
        //Log.d("wtffffff_ViewModel_PurchaseTicket", "getFromToDateDayName after value init");
        MutableLiveData<HashMap<String, String>> hashMapMutableLiveData = new MutableLiveData<>();
        //Log.d("wtffffff_ViewModel_PurchaseTicket", "getFromToDateDayName after hashMapMutableLiveData init");
        hashMapMutableLiveData.setValue(value);
        //Log.d("wtffffff_ViewModel_PurchaseTicket", "getFromToDateDayName after hashMapMutableLiveData set value");

        return (LiveData<HashMap<String, String>>) hashMapMutableLiveData;
    }

    //DepTime CompanyName AcStatus Fare SeatNo
    public void setDepTime_CompanyName_AcStatus_Fare_SeatNo(String dep_time, String company_name, boolean ac_status, double fare, String seat_no){

        //Log.d("wtffffff_ViewModel_PurchaseTicket", "setFromToDateDayName before init");
        IssueTicket it = issueTicketMutableLiveData.getValue();
        it.setDepTime_CompanyName_AcStatus_Fare_SeatNo(dep_time, company_name, ac_status, fare, seat_no);
        issueTicketMutableLiveData.setValue(it);
        //Log.d("wtffffff_ViewModel_PurchaseTicket", "setFromToDateDayName after init");
    }

    public LiveData<HashMap<String, String>> getDepTime_CompanyName_AcStatus_Fare_SeatNo(){
        //Log.d("wtffffff_ViewModel_PurchaseTicket", "getFromToDateDayName before value init");
        HashMap<String, String> value = issueTicketMutableLiveData.getValue().getDepTime_CompanyName_AcStatus_Fare_SeatNo();
        MutableLiveData<HashMap<String, String>> hashMapMutableLiveData = new MutableLiveData<>();
        hashMapMutableLiveData.setValue(value);
        //Log.d("wtffffff_ViewModel_PurchaseTicket", "getFromToDateDayName after hashMapMutableLiveData set value");

        return (LiveData<HashMap<String, String>>) hashMapMutableLiveData;
    }

    //user_phn_no Name Phn_no
    public void setUserPhnNo_Name_PhnNo(String user_phn_no, String name,  String phn_no){

        //Log.d("wtffffff_ViewModel_PurchaseTicket", "setFromToDateDayName before init");
        IssueTicket it = issueTicketMutableLiveData.getValue();
        it.setUserPhnNo_Name_PhnNo( user_phn_no,  name,  phn_no);
        issueTicketMutableLiveData.setValue(it);
        //Log.d("wtffffff_ViewModel_PurchaseTicket", "setFromToDateDayName after init");
    }

    public LiveData<HashMap<String, String>> getUserPhnNo_Name_PhnNo(){
        //Log.d("wtffffff_ViewModel_PurchaseTicket", "getFromToDateDayName before value init");
        HashMap<String, String> value = issueTicketMutableLiveData.getValue().getUserPhnNo_Name_PhnNo();
        MutableLiveData<HashMap<String, String>> hashMapMutableLiveData = new MutableLiveData<>();
        hashMapMutableLiveData.setValue(value);
        //Log.d("wtffffff_ViewModel_PurchaseTicket", "getFromToDateDayName after hashMapMutableLiveData set value");

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
}
