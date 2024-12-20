/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelManagement;

/**
 *
 * @author Electronica Care
 */

// For Builder Pattern

public class HotelRoom {
    private String roomNo;
    private String roomType;
    private String bedType;
    private int amount;

    // Private constructor
    private HotelRoom(HotelRoomBuilder builder) {
        this.roomNo = builder.roomNo;
        this.roomType = builder.roomType;
        this.bedType = builder.bedType;
        this.amount = builder.amount;
    }

    // Getters
    public String getRoomNo() { return roomNo; }
    public String getRoomType() { return roomType; }
    public String getBedType() { return bedType; }
    public int getAmount() { return amount; }

    // Static Inner Builder Class
    public static class HotelRoomBuilder {
        private String roomNo;
        private String roomType;
        private String bedType;
        private int amount;

        public HotelRoomBuilder setRoomNo(String roomNo) {
            this.roomNo = roomNo;
            return this;
        }

        public HotelRoomBuilder setRoomType(String roomType) {
            this.roomType = roomType;
            return this;
        }

        public HotelRoomBuilder setBedType(String bedType) {
            this.bedType = bedType;
            return this;
        }

        public HotelRoomBuilder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public HotelRoom build() {
            return new HotelRoom(this);
        }
    }
}