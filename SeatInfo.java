package com.example.traingood;

/**
 * Created by pacilo on 2017. 8. 31..
 */

public class SeatInfo {

    int chairNo;        // 1 ~ 216 ??
    String trainNo;     // 열차번호
    String destination; // 목적지 정보
    boolean isOccupied; // 좌석 착석유무
    String occupiedUserID;
    boolean isReserved; // 좌석 예약유무
    String reservedUserID;

    int chairNo2;
    String destination2;
    boolean isOccupied2;
    String occupiedUserID2;
    boolean isReserved2;

    public String getReservedUserID2() {
        return reservedUserID2;
    }

    public void setReservedUserID2(String reservedUserID2) {
        this.reservedUserID2 = reservedUserID2;
    }

    public String getReservedUserID() {
        return reservedUserID;
    }

    public void setReservedUserID(String reservedUserID) {
        this.reservedUserID = reservedUserID;
    }

    String reservedUserID2;

    public String getOccupiedUserID() {
        return occupiedUserID;
    }

    public void setOccupiedUserID(String occupiedUserID) {
        this.occupiedUserID = occupiedUserID;
    }

    public String getOccupiedUserID2() {
        return occupiedUserID2;
    }

    public void setOccupiedUserID2(String occupiedUserID2) {
        this.occupiedUserID2 = occupiedUserID2;
    }

    public SeatInfo() {
    }

    public SeatInfo(int _chairNo, int _chairNo2, String _trainNo, String _occupiedUser, String _occupiedUser2, String _reservedUser, String _reservedUser2) {
        chairNo = _chairNo;
        trainNo = _trainNo;
        destination = "";
        isOccupied = false;
        occupiedUserID = _occupiedUser;
        isReserved = false;
        reservedUserID = _reservedUser;

        chairNo2 = _chairNo2;
        destination2 = "";
        isOccupied2 = false;
        occupiedUserID2 = _occupiedUser2;
        isReserved2 = false;
        reservedUserID2 = _reservedUser2;
    }

    public int getChairNo2() {
        return chairNo2;
    }

    public void setChairNo2(int chairNo2) {
        this.chairNo2 = chairNo2;
    }

    public String getDestination2() {
        return destination2;
    }

    public void setDestination2(String destination2) {
        this.destination2 = destination2;
    }

    public boolean isOccupied2() {
        return isOccupied2;
    }

    public void setOccupied2(boolean occupied2) {
        isOccupied2 = occupied2;
    }

    public boolean isReserved2() {
        return isReserved2;
    }

    public void setReserved2(boolean reserved2) {
        isReserved2 = reserved2;
    }

    public int getChairNo() {
        return chairNo;
    }

    public void setChairNo(int chairNo) {
        this.chairNo = chairNo;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
}
