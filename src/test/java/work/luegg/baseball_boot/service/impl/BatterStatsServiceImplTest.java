package work.luegg.baseball_boot.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import work.luegg.baseball_boot.dto.BatterStatsDTO;
import work.luegg.baseball_boot.model.Batter_log;
import work.luegg.baseball_boot.repository.Batter_logRepository;



@ExtendWith(MockitoExtension.class)
  
public class BatterStatsServiceImplTest {

	@Mock
    private Batter_logRepository batterRepo;

    @InjectMocks
    private BatterStatsServiceImpl batterService;
    
    
    
	@Test
	void saveBatterStats_success() {
		BatterStatsDTO dto = new BatterStatsDTO();
		dto.setName("大谷");
		
		String result = batterService.saveBatterStats(dto,"AAA");
				
		assertEquals("打者資料已儲存", result);
		verify(batterRepo).save(any());
	}
	
	
	@Test
	void deleteBatter_success() {
		Batter_log batter = new Batter_log();
		batter.setId(1L);
		
		
		when(
			batterRepo.findByIdAndTeam(1L,"AAA")	
		).thenReturn(
			Optional.of(batter)	
		);
		
		batterService.deleteBatter(1L,"AAA");
		verify(batterRepo).delete(batter);
		
		
		
	}
	
	
	
	@Test 
	void getBatterById_success(){
		Batter_log batter = new Batter_log();
		batter.setId(1L);
		batter.setName("大谷");
	
		when(
			    batterRepo.findByIdAndTeam(1L, "AAA")
			).thenReturn(
			    Optional.of(batter)
			);
				
				
		BatterStatsDTO dto = batterService.getBatterById(1L,"AAA");
		assertEquals("大谷",dto.getName());						
		
	}
	
	
	
	
	
	
	@Test
	void updateBatter_success() {

	    Batter_log batter = new Batter_log();
	    batter.setId(1L);
	    batter.setName("王建民");

	    BatterStatsDTO dto = new BatterStatsDTO();
	    dto.setName("大谷");

	    when(
	        batterRepo.findByIdAndTeam(1L, "AAA")
	    ).thenReturn(
	        Optional.of(batter)
	    );

	    batterService.updateBatter(1L,dto,"AAA");

	    // 這裡驗證什麼？
	    assertEquals("大谷", batter.getName());
	    // 這裡 verify 什麼？
	    verify(batterRepo).save(batter);
	}
	
	
}
