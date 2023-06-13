package com.ifsp.lp.work.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifsp.lp.work.repository.UserRepository;
import com.ifsp.lp.work.dto.UserDTO;
import com.fasterxml.jackson.core.type.TypeReference;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    public UserService() {
        this.userRepository = userRepository;
    }


    public List<UserDTO> getAllUsers() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/files/DB.json");
        TypeReference<List<UserDTO>> typeReference = new TypeReference<List<UserDTO>>() {};
        List<UserDTO> userList = objectMapper.readValue(file, typeReference);
        return userList;
    }

    public List<UserDTO> initDB()
    {
        return combineUserLists();
    }

    public UserDTO getUserById(String userId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/files/DB.json");
        List<UserDTO> userList = objectMapper.readValue(file, new TypeReference<List<UserDTO>>() {});

        for (UserDTO user : userList) {
            String id = user.getId().toString();
            if (id.equals(userId)) {
                return user;
            }
        }

        return null;
    }

    public UserDTO createUser(UserDTO user) {
        return userRepository.saveUser(user);
    }




    public UserDTO updateUser(String userId, UserDTO updatedUser) {
        UserDTO existingUser = userRepository.getUserById(userId);
        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
        }
        return null;
    }
    
    public List<UserDTO> readUsersFromCSV() {
        List<UserDTO> users = new ArrayList<>();
        String filePath = "src/files/input-backend.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true; // Para ignorar o cabeçalho do CSV
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                line = line.replaceAll("\"", "");
                String[] data = line.split(",");
                // Extrair os valores do CSV e criar um objeto UserDTO
                UserDTO userDTO = new UserDTO();

                try {

                    userDTO.setGender(data[0]);
                    UserDTO.NameDTO nameDTO = new UserDTO.NameDTO();
                    nameDTO.setTitle(data[1]);
                    nameDTO.setFirst(data[2]);
                    nameDTO.setLast(data[3]);
                    userDTO.setName(nameDTO);

                    UserDTO.LocationDTO locationDTO = new UserDTO.LocationDTO();
                    locationDTO.setStreet(data[4]);
                    locationDTO.setCity(data[5]);
                    locationDTO.setState(data[6]);

                    UserDTO.CoordinatesDTO coordinatesDTO = new UserDTO.CoordinatesDTO();
                    coordinatesDTO.setLatitude(data[8]);
                    coordinatesDTO.setLongitude(data[9]);
                    locationDTO.setCoordinates(coordinatesDTO);

                    UserDTO.TimezoneDTO timezoneDTO = new UserDTO.TimezoneDTO();
                    timezoneDTO.setOffset(data[10]);
                    timezoneDTO.setDescription(data[11]);
                    locationDTO.setTimezone(timezoneDTO);

                    userDTO.setLocation(locationDTO);

                    userDTO.setEmail(data[12]);

                    UserDTO.DobDTO dobDTO = new UserDTO.DobDTO();
                    dobDTO.setDate(data[13]);
                    dobDTO.setAge(Integer.parseInt(data[14]));
                    userDTO.setDob(dobDTO);

                    UserDTO.RegisteredDTO registeredDTO = new UserDTO.RegisteredDTO();

                    registeredDTO.setDate(data[15]);
                    registeredDTO.setAge(Integer.parseInt(data[16]));
                    userDTO.setRegistered(registeredDTO);

                    userDTO.setPhone(data[17]);
                    userDTO.setCell(data[18]);

                    UserDTO.PictureDTO pictureDTO = new UserDTO.PictureDTO();
                    pictureDTO.setLarge(data[19]);
                    pictureDTO.setMedium(data[20]);
                    pictureDTO.setThumbnail(data[21]);
                    userDTO.setPicture(pictureDTO);

                    userDTO.setRegion(getUserRegion(data[8],data[9]));
                    userDTO.setUserType(classifyClient(data[8],data[9] ));


                } catch (NumberFormatException e) {
                    System.out.println("Erro na linha do CSV: " + line);
                    e.printStackTrace();
                }

                users.add(userDTO);
               // createUser(userDTO);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    public List<UserDTO> readUsersFromJSON() {
        List<UserDTO> users = new ArrayList<>();
        String filePath = "src/files/input.json";

        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(filePath));
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(jsonData);

            JsonNode usersNode = root.get("results");
            if (usersNode != null && usersNode.isArray()) {
                for (JsonNode userNode : usersNode) {
                    UserDTO userDTO = new UserDTO();

                    userDTO.setGender(userNode.get("gender").asText());

                    UserDTO.NameDTO nameDTO = new UserDTO.NameDTO();
                    JsonNode nameNode = userNode.get("name");
                    if (nameNode != null) {
                        nameDTO.setTitle(nameNode.get("title").asText());
                        nameDTO.setFirst(nameNode.get("first").asText());
                        nameDTO.setLast(nameNode.get("last").asText());
                    }
                    userDTO.setName(nameDTO);

                    UserDTO.LocationDTO locationDTO = new UserDTO.LocationDTO();
                    JsonNode locationNode = userNode.get("location");
                    if (locationNode != null) {
                        locationDTO.setStreet(locationNode.get("street").asText());
                        locationDTO.setCity(locationNode.get("city").asText());
                        locationDTO.setState(locationNode.get("state").asText());

                        UserDTO.CoordinatesDTO coordinatesDTO = new UserDTO.CoordinatesDTO();
                        JsonNode coordinatesNode = locationNode.get("coordinates");
                        if (coordinatesNode != null) {
                            coordinatesDTO.setLatitude(coordinatesNode.get("latitude").asText());
                            coordinatesDTO.setLongitude(coordinatesNode.get("longitude").asText());
                        }
                        locationDTO.setCoordinates(coordinatesDTO);

                        UserDTO.TimezoneDTO timezoneDTO = new UserDTO.TimezoneDTO();
                        JsonNode timezoneNode = locationNode.get("timezone");
                        if (timezoneNode != null) {
                            timezoneDTO.setOffset(timezoneNode.get("offset").asText());
                            timezoneDTO.setDescription(timezoneNode.get("description").asText());
                        }
                        locationDTO.setTimezone(timezoneDTO);
                    }
                    userDTO.setLocation(locationDTO);

                    userDTO.setEmail(userNode.get("email").asText());

                    UserDTO.DobDTO dobDTO = new UserDTO.DobDTO();
                    JsonNode dobNode = userNode.get("dob");
                    if (dobNode != null) {
                        dobDTO.setDate(dobNode.get("date").asText());
                        dobDTO.setAge(dobNode.get("age").asInt());
                    }
                    userDTO.setDob(dobDTO);

                    UserDTO.RegisteredDTO registeredDTO = new UserDTO.RegisteredDTO();
                    JsonNode registeredNode = userNode.get("registered");
                    if (registeredNode != null) {
                        registeredDTO.setDate(registeredNode.get("date").asText());
                        registeredDTO.setAge(registeredNode.get("age").asInt());
                    }
                    userDTO.setRegistered(registeredDTO);

                    userDTO.setPhone(userNode.get("phone").asText());
                    userDTO.setCell(userNode.get("cell").asText());

                    UserDTO.PictureDTO pictureDTO = new UserDTO.PictureDTO();
                    JsonNode pictureNode = userNode.get("picture");
                    if (pictureNode != null) {
                        pictureDTO.setLarge(pictureNode.get("large").asText());
                        pictureDTO.setMedium(pictureNode.get("medium").asText());
                        pictureDTO.setThumbnail(pictureNode.get("thumbnail").asText());
                    }
                    userDTO.setPicture(pictureDTO);

                    userDTO.setRegion(getUserRegion(locationDTO.getCoordinates().getLatitude(), locationDTO.getCoordinates().getLongitude()));
                    userDTO.setUserType(classifyClient(locationDTO.getCoordinates().getLatitude(), locationDTO.getCoordinates().getLongitude()));

                    users.add(userDTO);
                  // createUser(userDTO);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    public List<UserDTO> combineUserLists() {
        List<UserDTO> combinedUsers = new ArrayList<>();

        // Ler lista de usuários do CSV
        List<UserDTO> csvUsers = readUsersFromCSV();
        combinedUsers.addAll(csvUsers);

        // Ler lista de usuários do JSON
        List<UserDTO> jsonUsers = readUsersFromJSON();
        combinedUsers.addAll(jsonUsers);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("src/files/DB.json"), combinedUsers);
            System.out.println("Lista salva como JSON com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar lista como JSON: " + e.getMessage());
        }

        return combinedUsers;
    }

    public String classifyClient(String latitudeStr, String longitudeStr) {

        double latitude = Double.parseDouble(latitudeStr);
        double longitude = Double.parseDouble(longitudeStr);
        // Bounding Box ESPECIAL
        double minlonEspecial = -2.196998;
        double minlatEspecial = -46.361899;
        double maxlonEspecial = -15.411580;
        double maxlatEspecial = -34.276938;

        // Bounding Box NORMAL
        double minlonNormal = -26.155681;
        double minlatNormal = -54.777426;
        double maxlonNormal = -34.016466;
        double maxlatNormal = -46.603598;

        if (latitude >= minlatEspecial && latitude <= maxlatEspecial && longitude >= minlonEspecial && longitude <= maxlonEspecial) {
            return "ESPECIAL";
        } else if (latitude >= minlatNormal && latitude <= maxlatNormal && longitude >= minlonNormal && longitude <= maxlonNormal) {
            return "NORMAL";
        } else {
            return "INDEFINIDO";
        }
    }
    public String getUserRegion(String latitudeStr, String longitudeStr) {

        double latitude = Double.parseDouble(latitudeStr);
        double longitude = Double.parseDouble(longitudeStr);
        if (latitude >= -5.0 && latitude <= 5.0) {
            return "Região Norte";
        } else if (latitude > 5.0 && latitude <= 0.0 && longitude >= -45.0 && longitude <= -35.0) {
            return "Região Nordeste";
        } else if (latitude > 0.0 && latitude <= -10.0 && longitude >= -65.0 && longitude <= -50.0) {
            return "Região Centro-Oeste";
        } else if (latitude > -20.0 && latitude <= -10.0 && longitude >= -50.0 && longitude <= -40.0) {
            return "Região Sudeste";
        } else if (latitude > -30.0 && latitude <= -20.0 && longitude >= -60.0 && longitude <= -45.0) {
            return "Região Sul";
        } else {
            return "Região não identificada";
        }
    }
    public boolean deleteUser(String userId) {
        userRepository.deleteUser(userId);
		return true;
    }
}
