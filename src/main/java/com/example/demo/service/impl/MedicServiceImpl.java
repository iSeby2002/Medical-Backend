package com.example.demo.service.impl;

import com.example.demo.dtos.*;
import com.example.demo.model.Medic;
import com.example.demo.repository.MedicRepository;
import com.example.demo.service.MedicService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

@Service
public class MedicServiceImpl implements MedicService {

    private final MedicRepository medicRepository;

    public MedicServiceImpl(MedicRepository medicRepository){
        this.medicRepository = medicRepository;
    }

    @Override
    public LoginResponseDTO logIn(LoginDto loginDto) {
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        Medic medic = medicRepository.findFirstByEmail(loginDto.getEmail());
        if(medic == null){
            loginResponseDTO.setMedic(null);
            loginResponseDTO.setMesaj("Nu există cont cu acest email!");
        }else{
            if(hashPassword(loginDto.getPassword()).equals(medic.getPassword())) {
                loginResponseDTO.setMedic(medic);
                loginResponseDTO.setMesaj("Logare cu succes!");
            }else{
                loginResponseDTO.setMedic(null);
                loginResponseDTO.setMesaj("Email-ul sau parola sunt incorecte!");
            }
        }
        return loginResponseDTO;
    }

    @Override
    public RegisterResponseDTO register(RegisterDto registerDto) {
        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO();
        Medic medic = medicRepository.findFirstByEmail(registerDto.getEmail());
        if(medic != null){
            registerResponseDTO.setMedic(null);
            registerResponseDTO.setMesaj("Există deja un cont acest email!");
        }else if(!isValidRole(registerDto.getRole())) {
            registerResponseDTO.setMedic(null);
            registerResponseDTO.setMesaj("Rol invalid. Rolul trebuie sa fie \"oftalmolog\" sau \"diabetolog\" !");
        }else{
            Medic medicNou = Medic.builder()
                    .nume(registerDto.getNume())
                    .prenume(registerDto.getPrenume())
                    .email(registerDto.getEmail())
                    .password(hashPassword(registerDto.getPassword()))
                    .phoneNumber(registerDto.getPhoneNumber())
                    .role(registerDto.getRole())
                    .build();
            medicRepository.save(medicNou);
            registerResponseDTO.setMedic(medicNou);
            registerResponseDTO.setMesaj("Înregistrare cu succes!");
        }
        return registerResponseDTO;
    }

    @Override
    public List<Medic> getMedici() {
        List<Medic> medici = (List<Medic>) medicRepository.findAll();
        medici.removeIf(medic -> medic.getRole().equals("admin"));
        return medici;
    }

    @Override
    public UpdateResponseDTO update(Medic medicUpdate) {
        UpdateResponseDTO updateResponseDTO = new UpdateResponseDTO();
        Medic medic = medicRepository.findMedicById(medicUpdate.getId());
        if(medic == null){
            updateResponseDTO.setMedic(null);
            updateResponseDTO.setMesaj("Nu există medicul selectat!");
        }else{
            List<Medic>medici = (List<Medic>) medicRepository.findAll();
            medici.remove(medic);
            boolean exista=false;
            for(Medic m:medici){
                if (m.getEmail().equals(medicUpdate.getEmail())) {
                    exista = true;
                    break;
                }
            }
            if(exista){
                updateResponseDTO.setMedic(null);
                updateResponseDTO.setMesaj("Există deja un cont cu acest email!");
            }else if(!isValidRole(medicUpdate.getRole())) {
                updateResponseDTO.setMedic(null);
                updateResponseDTO.setMesaj("Rol invalid. Rolul trebuie sa fie \"oftalmolog\" sau \"diabetolog\" !");
            }else{
                medic.setPrenume(medicUpdate.getPrenume());
                medic.setNume(medicUpdate.getNume());
                medic.setEmail(medicUpdate.getEmail());
                medic.setPhoneNumber(medicUpdate.getPhoneNumber());
                medic.setRole(medicUpdate.getRole());
                medicRepository.save(medic);
                updateResponseDTO.setMedic(medic);
                updateResponseDTO.setMesaj("Actualizare reușită!");
            }
        }
        return updateResponseDTO;
    }

    @Override
    public UpdateResponseDTO schimbareParola(Medic medicUpdate) {
        UpdateResponseDTO updateResponseDTO = new UpdateResponseDTO();
        Medic medic = medicRepository.findMedicById(medicUpdate.getId());
        if(medic == null){
            updateResponseDTO.setMedic(null);
            updateResponseDTO.setMesaj("Nu există medicul selectat!");
        }else{
            if(hashPassword(medicUpdate.getPassword()).equals(medic.getPassword())){
                updateResponseDTO.setMedic(null);
                updateResponseDTO.setMesaj("Noua parolă nu poate să fie la fel cu cea anterioară!");
            }else{
                medic.setPassword(hashPassword(medicUpdate.getPassword()));
                medicRepository.save(medic);
                updateResponseDTO.setMedic(medic);
                updateResponseDTO.setMesaj("Schimbare parolă reușită!");
            }
        }
        return updateResponseDTO;
    }

    @Override
    public String delete(Medic medicDelete) {
        Medic medic = medicRepository.findMedicById(medicDelete.getId());
        if(medic == null){
            return "Nu există medicul selectat!";
        }else {
            medicRepository.delete(medicDelete);
            return "Ștergere reușită!";
        }
    }

    private boolean isValidRole(String role) {
        return "oftalmolog".equalsIgnoreCase(role) || "diabetolog".equalsIgnoreCase(role) || "admin".equalsIgnoreCase(role);
    }

    private String hashPassword(String password) {
        try {
            // Sercured Hash Algorithm - 256
            // 1 byte = 8 biți
            // 1 byte = 1 char
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}