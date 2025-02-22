package com.ninos.services.impl;

import com.ninos.dtos.Response;
import com.ninos.dtos.RoomDTO;
import com.ninos.entities.Room;
import com.ninos.enums.RoomType;
import com.ninos.repositories.RoomRepository;
import com.ninos.services.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    private static final String IMAGE_DIRECTORY = System.getProperty("user.dir") + "/product-image/";


    @Override
    public Response addRoom(RoomDTO roomDTO, MultipartFile imageFile) {
        Room roomToSave = modelMapper.map(roomDTO, Room.class);
        if(imageFile != null) {
            String imagePath = saveImage(imageFile);
            roomToSave.setImageUrl(imagePath);
        }

        roomRepository.save(roomToSave);

        return Response.builder()
                .status(200)
                .message("Successfully added room")
                .build();
    }

    @Override
    public Response updateRoom(RoomDTO roomDTO, MultipartFile imageFile) {
        return null;
    }

    @Override
    public Response getAllRooms() {
        return null;
    }

    @Override
    public Response getRoomById(Long id) {
        return null;
    }

    @Override
    public Response deleteRoom(Long id) {
        return null;
    }

    @Override
    public Response getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, RoomType roomType) {
        return null;
    }

    @Override
    public List<RoomType> getAllRoomTypes() {
        return List.of();
    }

    @Override
    public Response searchRoom() {
        return null;
    }



    private String saveImage(MultipartFile imageFile) {
        if (!imageFile.getContentType().startsWith("image/")){
            throw new IllegalArgumentException("Only image files are allowed");
        }

        // create directory to store image if it does not exist
        File directory = new File(IMAGE_DIRECTORY);

        if(!directory.exists()){
            directory.mkdir();
        }

        // generate unique file name for the image
        String uniqueFileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();

        // get the absolute path of the image
        String imagePath = IMAGE_DIRECTORY + uniqueFileName;

        try {
          File destinationFile = new File(imagePath);
          imageFile.transferTo(destinationFile);
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }

        return imagePath;
    }





}
