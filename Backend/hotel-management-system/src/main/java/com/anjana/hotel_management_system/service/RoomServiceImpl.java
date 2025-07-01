package com.anjana.hotel_management_system.service;

import com.anjana.hotel_management_system.exception.InternalServerException;
import com.anjana.hotel_management_system.exception.ResourceNotFoundException;
import com.anjana.hotel_management_system.model.Room;
import com.anjana.hotel_management_system.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    @Override
    public Room addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice) throws IOException, SQLException {
        Room room=new Room();
        room.setRoomType(roomType);
        room.setRoomPrice(roomPrice);
        byte[] photoBytes=photo.getBytes();
        Blob photoBlob=new SerialBlob(photoBytes);
        room.setPhoto(photoBlob);
        return roomRepository.save(room);
    }

    @Override
    public List<String> getAllRoomTypes() {
        return roomRepository.findDistinctRoomTypes();
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public byte[] getRoomPhotoByRoomId(Long roomId) throws SQLException {
        Optional<Room> room=roomRepository.findById(roomId);
        if(room.isEmpty()){
            throw new ResourceNotFoundException("Sorry! Room Not Found");
        }
        Blob photoBlob=room.get().getPhoto();
        if(photoBlob!=null){
            return photoBlob.getBytes(1,(int)photoBlob.length());
        }
        return null;
    }

    @Override
    public void deleteRoom(Long roomId) {
        Optional<Room> theRoom = roomRepository.findById(roomId);
        if(theRoom.isPresent()){
            roomRepository.deleteById(roomId);
        }
    }

    @Override
    public Room updateRoom(Long roomId, String roomType, BigDecimal roomPrice, byte[] photoBytes){
        System.out.println("type"+roomType+"\nprice"+roomPrice);
        Room room = roomRepository.findById(roomId).get();
        if (roomType != null) room.setRoomType(roomType);
        if (roomPrice != null) room.setRoomPrice(roomPrice);
        if (photoBytes != null && photoBytes.length > 0) {
            try {
                room.setPhoto(new SerialBlob(photoBytes));
            } catch (SQLException ex) {
                throw new InternalServerException("Fail updating room");
            }
        }
        return roomRepository.save(room);
    }

    @Override
    public Optional<Room> getRoomById(Long roomId) {
        return Optional.of(roomRepository.findById(roomId).get());
    }

    @Override
    public List<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, String roomType) {
        return roomRepository.findAvailableRoomsByDatesAndType(checkInDate, checkOutDate, roomType);
    }


}
