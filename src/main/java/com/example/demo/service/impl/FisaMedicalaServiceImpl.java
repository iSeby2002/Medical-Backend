package com.example.demo.service.impl;

import com.example.demo.dtos.FisaMedicalaResponseDTO;
import com.example.demo.model.FisaMedicala;
import com.example.demo.model.Programari;
import com.example.demo.repository.FisaMedicalaRepository;
import com.example.demo.repository.ProgramariRepository;
import com.example.demo.service.FisaMedicalaService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FisaMedicalaServiceImpl implements FisaMedicalaService {

    private final ProgramariRepository programariRepository;
    private final FisaMedicalaRepository fisaMedicalaRepository;

    public FisaMedicalaServiceImpl(ProgramariRepository programariRepository, FisaMedicalaRepository fisaMedicalaRepository) {
        this.programariRepository = programariRepository;
        this.fisaMedicalaRepository = fisaMedicalaRepository;
    }

    @Override
    public List<Programari> findProgramariCurente(LocalDateTime startTime) {
        LocalDateTime timeCurent = startTime;
        LocalDateTime startOfDay = timeCurent.toLocalDate().atStartOfDay();
        return programariRepository.findAllByDate(startOfDay);
    }

    @Override
    public String saveFisaMedicala(FisaMedicala fisaMedicala) {
        FisaMedicala fisaMedicalaExistenta =fisaMedicalaRepository.findFisaMedicalaByProgramari(fisaMedicala.getProgramari());
        if(fisaMedicalaExistenta!=null){
            fisaMedicalaExistenta.setProgramari(fisaMedicala.getProgramari());
            fisaMedicalaExistenta.setNrCrt(fisaMedicala.getNrCrt());
            fisaMedicalaExistenta.setTip1DiabetZaharat(fisaMedicala.getTip1DiabetZaharat());
            fisaMedicalaExistenta.setTip2DiabetZaharat(fisaMedicala.getTip2DiabetZaharat());
            fisaMedicalaExistenta.setDataDiagnosticului(fisaMedicala.getDataDiagnosticului());
            fisaMedicalaExistenta.setHbA1C(fisaMedicala.getHbA1C());
            fisaMedicalaExistenta.setMaiMic6Luni(fisaMedicala.getMaiMic6Luni());
            fisaMedicalaExistenta.setMaiMare6Luni(fisaMedicala.getMaiMare6Luni());
            fisaMedicalaExistenta.setGlicemie(fisaMedicala.getGlicemie());
            fisaMedicalaExistenta.setUree(fisaMedicala.getUree());
            fisaMedicalaExistenta.setCreatinina(fisaMedicala.getCreatinina());
            fisaMedicalaExistenta.setERFG(fisaMedicala.getERFG());
            fisaMedicalaExistenta.setHta(fisaMedicala.getHta());
            fisaMedicalaExistenta.setNeuropatie(fisaMedicala.getNeuropatie());
            fisaMedicalaExistenta.setNefropatie(fisaMedicala.getNefropatie());
            fisaMedicalaExistenta.setCi(fisaMedicala.getCi());
            fisaMedicalaExistenta.setAvc(fisaMedicala.getAvc());
            fisaMedicalaExistenta.setIma(fisaMedicala.getIma());
            fisaMedicalaExistenta.setHipercolesterolemie(fisaMedicala.getHipercolesterolemie());
            fisaMedicalaExistenta.setHipertrigliceridemie(fisaMedicala.getHipertrigliceridemie());
            fisaMedicalaExistenta.setInsulina(fisaMedicala.getInsulina());
            fisaMedicalaExistenta.setAdo(fisaMedicala.getAdo());
            fisaMedicalaExistenta.setDieta(fisaMedicala.getDieta());
            fisaMedicalaExistenta.setNimic(fisaMedicala.getNimic());
            fisaMedicalaExistenta.setAcuitateVizualaOD(fisaMedicala.getAcuitateVizualaOD());
            fisaMedicalaExistenta.setAcuitateVizualaOS(fisaMedicala.getAcuitateVizualaOS());
            fisaMedicalaExistenta.setRubeozaIrianaOD(fisaMedicala.getRubeozaIrianaOD());
            fisaMedicalaExistenta.setRubeozaIrianaOS(fisaMedicala.getRubeozaIrianaOS());
            fisaMedicalaExistenta.setFaraRetinopatieDiabeticaOD(fisaMedicala.getFaraRetinopatieDiabeticaOD());
            fisaMedicalaExistenta.setFaraRetinopatieDiabeticaOS(fisaMedicala.getFaraRetinopatieDiabeticaOS());
            fisaMedicalaExistenta.setRetinopatieDiabeticaNeproliferativaUsoaraOD(fisaMedicala.getRetinopatieDiabeticaNeproliferativaUsoaraOD());
            fisaMedicalaExistenta.setRetinopatieDiabeticaNeproliferativaUsoaraOS(fisaMedicala.getRetinopatieDiabeticaNeproliferativaUsoaraOS());
            fisaMedicalaExistenta.setRetinopatieDiabeticaNeproliferativaModerataOD(fisaMedicala.getRetinopatieDiabeticaNeproliferativaModerataOD());
            fisaMedicalaExistenta.setRetinopatieDiabeticaNeproliferativaModerataOS(fisaMedicala.getRetinopatieDiabeticaNeproliferativaModerataOS());
            fisaMedicalaExistenta.setRetinopatieDiabeticaNeproliferativaSeveraOD(fisaMedicala.getRetinopatieDiabeticaNeproliferativaSeveraOD());
            fisaMedicalaExistenta.setRetinopatieDiabeticaNeproliferativaSeveraOS(fisaMedicala.getRetinopatieDiabeticaNeproliferativaSeveraOS());
            fisaMedicalaExistenta.setRetinopatieDiabeticaProliferativaOD(fisaMedicala.getRetinopatieDiabeticaProliferativaOD());
            fisaMedicalaExistenta.setRetinopatieDiabeticaProliferativaOS(fisaMedicala.getRetinopatieDiabeticaProliferativaOS());
            fisaMedicalaExistenta.setEdemMacularClinicSemnificativOD(fisaMedicala.getEdemMacularClinicSemnificativOD());
            fisaMedicalaExistenta.setEdemMacularClinicSemnificativOS(fisaMedicala.getEdemMacularClinicSemnificativOS());
            fisaMedicalaExistenta.setComparativCuUltimaExaminareLaFelOD(fisaMedicala.getComparativCuUltimaExaminareLaFelOD());
            fisaMedicalaExistenta.setComparativCuUltimaExaminareLaFelOS(fisaMedicala.getComparativCuUltimaExaminareLaFelOS());
            fisaMedicalaExistenta.setComparativCuUltimaExaminareMaiBineOD(fisaMedicala.getComparativCuUltimaExaminareMaiBineOD());
            fisaMedicalaExistenta.setComparativCuUltimaExaminareMaiBineOS(fisaMedicala.getComparativCuUltimaExaminareMaiBineOS());
            fisaMedicalaExistenta.setComparativCuUltimaExaminareMaiRauOD(fisaMedicala.getComparativCuUltimaExaminareMaiRauOD());
            fisaMedicalaExistenta.setComparativCuUltimaExaminareMaiRauOS(fisaMedicala.getComparativCuUltimaExaminareMaiRauOS());
            fisaMedicalaExistenta.setComparativCuUltimaExaminareNuSeCunoasteOD(fisaMedicala.getComparativCuUltimaExaminareNuSeCunoasteOD());
            fisaMedicalaExistenta.setComparativCuUltimaExaminareNuSeCunoasteOS(fisaMedicala.getComparativCuUltimaExaminareNuSeCunoasteOS());
            fisaMedicalaExistenta.setDetaliiFundDeOchi(fisaMedicala.getDetaliiFundDeOchi());
            fisaMedicalaExistenta.setAlteModalitatiOculare(fisaMedicala.getAlteModalitatiOculare());
            fisaMedicalaExistenta.setInjectieNumarOD(fisaMedicala.getInjectieNumarOD());
            fisaMedicalaExistenta.setInjectieDozaOD(fisaMedicala.getInjectieDozaOD());
            fisaMedicalaExistenta.setInjectieNumarOS(fisaMedicala.getInjectieNumarOS());
            fisaMedicalaExistenta.setInjectieDozaOS(fisaMedicala.getInjectieDozaOS());
            fisaMedicalaExistenta.setLaserOD(fisaMedicala.getLaserOD());
            fisaMedicalaExistenta.setLaserOS(fisaMedicala.getLaserOS());
            fisaMedicalaExistenta.setDiagnosticOD(fisaMedicala.getDiagnosticOD());
            fisaMedicalaExistenta.setDiagnosticOS(fisaMedicala.getDiagnosticOS());
            fisaMedicalaExistenta.setDoarMonitorizare(fisaMedicala.getDoarMonitorizare());
            fisaMedicalaExistenta.setExaminareSuplimentara(fisaMedicala.getExaminareSuplimentara());
            fisaMedicalaExistenta.setExaminareSuplimentaraField(fisaMedicala.getExaminareSuplimentaraField());
            fisaMedicalaExistenta.setTratament(fisaMedicala.getTratament());
            fisaMedicalaExistenta.setTratamentField(fisaMedicala.getTratamentField());
            fisaMedicalaExistenta.setPeste1An(fisaMedicala.getPeste1An());
            fisaMedicalaExistenta.setPesteLuni(fisaMedicala.getPesteLuni());
            fisaMedicalaExistenta.setPesteSaptamani(fisaMedicala.getPesteSaptamani());
            fisaMedicalaExistenta.setAmbulator(fisaMedicala.getAmbulator());
            fisaMedicalaExistenta.setAmbulatorLaField(fisaMedicala.getAmbulatorLaField());
            fisaMedicalaExistenta.setAmbulatorInField(fisaMedicala.getAmbulatorInField());
            fisaMedicalaExistenta.setData(fisaMedicala.getData());
            fisaMedicalaExistenta.setMedicExaminator(fisaMedicala.getMedicExaminator());
            fisaMedicalaRepository.save(fisaMedicalaExistenta);
        }else {
            fisaMedicalaRepository.save(fisaMedicala);
        }
        return "Salvare cu succes";
    }

    @Override
    public FisaMedicalaResponseDTO findAll() {
        FisaMedicalaResponseDTO fisaResponse = new FisaMedicalaResponseDTO();
        fisaResponse.setFiseMedicale((List<FisaMedicala>) fisaMedicalaRepository.findAll());
        fisaResponse.setMesaj("Succes!");
        return fisaResponse;
    }

    @Override
    public FisaMedicalaResponseDTO findAllByCnp(Long cnp){
        FisaMedicalaResponseDTO fisaResponse = new FisaMedicalaResponseDTO();
        List<FisaMedicala> fiseCautate = fisaMedicalaRepository.findByProgramari_Pacient_Cnp(cnp);
        if(!fiseCautate.isEmpty()){
            fisaResponse.setFiseMedicale(fiseCautate);
            fisaResponse.setMesaj("Succes!");
        } else {
            fisaResponse.setFiseMedicale(null);
            fisaResponse.setMesaj("Nu există fișă medicală pentru acest CNP!");
        }
        return fisaResponse;
    }

    @Override
    public FisaMedicala findFisaMedicalaByProgramare(Programari programare) {
        Programari programareExistenta = programariRepository.findProgramariByStartTime(programare.getStartTime());
        FisaMedicala fisaMedicala = fisaMedicalaRepository.findFisaMedicalaByProgramari(programareExistenta);
        if(fisaMedicala == null){
            fisaMedicala = new FisaMedicala();
            fisaMedicala.setProgramari(programareExistenta);
        }
        return fisaMedicala;
    }
}