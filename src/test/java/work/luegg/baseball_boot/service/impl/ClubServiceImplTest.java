package work.luegg.baseball_boot.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import work.luegg.baseball_boot.model.Club;
import work.luegg.baseball_boot.repository.ClubRepository;

@ExtendWith(MockitoExtension.class)
class ClubServiceImplTest {

    @Mock
    private ClubRepository repo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ClubServiceImpl clubService;

    
    
    
    
    @Test
    void register_teamIsNull() {

        Club club = new Club();
        club.setTeam(null);
        club.setPassword("123");

        String result = clubService.register(club);

        assertEquals("隊名不能空白", result);
    }
    
    
    
    
    
    
    
    @Test
    void register_accountExists() {

        Club club = new Club();
        club.setTeam("AAA");
        club.setPassword("123");

        when(repo.existsByTeam("AAA"))
                .thenReturn(true);

        String result = clubService.register(club);

        assertEquals("帳號已存在", result);
    }
    
    
    
    
    @Test
    void register_success() {

        Club club = new Club();
        club.setTeam("AAA");
        club.setPassword("123");

        when(repo.existsByTeam("AAA"))
                .thenReturn(false);

        when(passwordEncoder.encode("123"))
                .thenReturn("加密後密碼");

        String result = clubService.register(club);

        assertEquals("註冊成功", result);

        verify(repo).save(club);
    }
    
    
    
    
    
    
    @Test
    void login_accountNotFound() {

        Club club = new Club();
        club.setTeam("AAA");
        club.setPassword("123");

        when(repo.findById("AAA"))
                .thenReturn(Optional.empty());

        String result = clubService.login(club);

        assertEquals("帳號不存在", result);
    }
    
    
    
    
    
    @Test
    void login_wrongPassword() {

        Club club = new Club();
        club.setTeam("AAA");
        club.setPassword("123");

        Club dbClub = new Club();
        dbClub.setTeam("AAA");
        dbClub.setPassword("加密後密碼");

        when(repo.findById("AAA"))
                .thenReturn(Optional.of(dbClub));

        when(passwordEncoder.matches("123", "加密後密碼"))
                .thenReturn(false);

        String result = clubService.login(club);

        assertEquals("密碼錯誤", result);
    }
    
    
    
    
    @Test
    void login_success() {

        Club club = new Club();
        club.setTeam("AAA");
        club.setPassword("123");

        Club dbClub = new Club();
        dbClub.setTeam("AAA");
        dbClub.setPassword("加密後密碼");

        when(repo.findById("AAA"))
                .thenReturn(Optional.of(dbClub));

        when(passwordEncoder.matches("123", "加密後密碼"))
                .thenReturn(true);

        String result = clubService.login(club);

        assertEquals("success", result);
    }
    
    
    
}