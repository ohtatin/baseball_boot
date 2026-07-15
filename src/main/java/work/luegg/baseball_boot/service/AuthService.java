package work.luegg.baseball_boot.service;

import work.luegg.baseball_boot.dto.ClubDTO;

public interface AuthService {

    String login(ClubDTO dto);
}