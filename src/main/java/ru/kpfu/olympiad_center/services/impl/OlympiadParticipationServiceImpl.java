package ru.kpfu.olympiad_center.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.olympiad_center.repositories.OlympiadParticipationRepository;
import ru.kpfu.olympiad_center.services.OlympiadParticipationService;

/**
 * 27.05.2018
 *
 * @author Robert Bagramov.
 */
@Service
public class OlympiadParticipationServiceImpl implements OlympiadParticipationService {
    private final OlympiadParticipationRepository participationRepository;

    @Autowired
    public OlympiadParticipationServiceImpl(OlympiadParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

//    @Override
//    public OlympiadParticipationDto getOlympiadParticipationByUserIdAndOlympiadId(Student user, Olympiad olympiad) {
//        Optional<OlympiadParticipation> participationOptional =
//                participationRepository.findByOlympiadAndUser(user, olympiad);
//
//        OlympiadParticipation olympiadParticipation = participationOptional
//                .orElseThrow(
//                        () -> new ParticipationNotFoundException(
//                                format("Participation not found by userId [%d] and olympiadId [%d]", user.getId(), olympiad.getId())));
//
//        return OlympiadParticipationDto.buildFromModel(olympiadParticipation);
//    }
}
