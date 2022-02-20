package project.woori_saza.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.woori_saza.model.dto.PartyDto;
import project.woori_saza.model.dto.PartyResponseDto;
import project.woori_saza.model.service.PartyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/party")
@Api("파티 컨트롤러")
public class PartyController {
    @Autowired
    PartyService partyService;

//    내 파티리스트 전체 조회
      @ApiOperation(value="마이사자에서 내 파티 리스트 조회",notes = "사용자의 파티 리스트를 반환다.")
    @GetMapping("/{profileId}")
    public ResponseEntity<List<PartyResponseDto>> GetPartyList(@PathVariable("profileId")String profileId){
        List<PartyResponseDto> list= partyService.getPartyList(profileId);
        return new ResponseEntity<List<PartyResponseDto>>(list, HttpStatus.OK);
    }

    //파티리스트 디테일 리스트 조회
    @ApiOperation(value="파티 디테일 리스트 조회",notes = "파티에 해당하는 사용자 정보들을 확인할 수 있다.")
    @GetMapping
    public ResponseEntity<List<PartyDto>> getDetailList(@RequestParam("partyId") @ApiParam(value="파티 아이디",required = true)Long partyId){
          List<PartyDto> party=partyService.getDetailList(partyId);
          return new ResponseEntity<List<PartyDto>>(party,HttpStatus.OK);
    }


    //파티리스트 삭제
    @ApiOperation(value="파티 삭제",notes = "파티를 삭제한다.")
    @DeleteMapping("/{partyId}")
    public ResponseEntity<Map<String, Object>> deleteParty(@PathVariable  @ApiParam(value="파티 아이디",required = true) Long partyId){
        Map<String, Object> result = new HashMap<>();
        HttpStatus httpStatus = null;
        try{
            partyService.deleteParty(partyId);
            httpStatus = HttpStatus.OK;
            result.put("success", true);
        }catch (RuntimeException e){
            e.printStackTrace();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);
        }
        return new ResponseEntity<Map<String,Object>>(result, httpStatus);
    }


    @ApiOperation(value="파티 마감, 구매 확정",notes="파티를 마감하고 구매확정한다.")
    @GetMapping("/finish/{partyId}")
    public ResponseEntity<Map<String,Object>> finishParty(@PathVariable @ApiParam( value="파티 아이디",required = true) Long partyId){
        Map<String,Object>result=new HashMap<>();
        HttpStatus status=null;
        try{
            partyService.finishParty(partyId);
            status=HttpStatus.OK;
            result.put("success", true);
        }catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);
        }
        return new ResponseEntity<Map<String, Object>>(result, status);
    }

}

