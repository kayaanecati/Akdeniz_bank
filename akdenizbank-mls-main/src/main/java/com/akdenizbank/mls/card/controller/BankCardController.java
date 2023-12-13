package com.akdenizbank.mls.card.controller;

import com.akdenizbank.mls.account.BankAccount;
import com.akdenizbank.mls.account.service.BankAccountService;
import com.akdenizbank.mls.xaction.UpdateBankCardXAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.akdenizbank.mls.card.BankCard;
import com.akdenizbank.mls.card.service.BankCardService;
import com.akdenizbank.mls.generic.rest.GenericApiResponse;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/bank-cards")
public class BankCardController {

    @Autowired
    private BankCardService bankCardService;

    @Autowired
    private BankAccountService bankAccountService;

    //TODO : CREATE BANK CARD METHOD
    //TODO : UPDATE BANK CARD METHOD
    @GetMapping
    public GenericApiResponse getAllBankCards(Pageable pageable) {
        Page<BankCard> bankCardsPage = this.bankCardService.getAll(pageable);
        return new GenericApiResponse(200, "Success", "345732945213", bankCardsPage);
    }
    @PostMapping("/{bankaccountid}")
    public GenericApiResponse createBankCard(@PathVariable String bankaccountid){
        BankAccount bankAccountInDB = this.bankAccountService.getById(bankaccountid);
        if(bankAccountInDB == null){
            throw new RuntimeException("No Such Bank Account");
        }
        BankCard bankCard = new BankCard();
        bankCard.setBankaccount(bankAccountInDB);
        bankAccountInDB.setLinkedcards((Set<BankCard>) bankCard);
        bankCardService.create(bankCard);
        return new GenericApiResponse(200,"Success","0001#0021",bankCard);
    }

    @PatchMapping("/{id}")
    public GenericApiResponse updateExpireDate(@PathVariable String id, @RequestBody UpdateBankCardXAction xaction){
        BankCard bankCardInDB = this.bankCardService.getById(id);
        if(bankCardInDB == null){
            throw new RuntimeException("No such bank card");
        }
        bankCardInDB.setExpiredate(xaction.getExpiredate());
        bankCardInDB =bankCardService.create(bankCardInDB);
        return new GenericApiResponse(200,"Success","0001#0020", bankCardInDB);
    }


    @GetMapping("/{id}")
    public GenericApiResponse getById(@PathVariable String id) {
        BankCard bankCardInDB = this.bankCardService.getById(id);
        if (bankCardInDB == null) {
            throw new RuntimeException("No Such Bank Card");
        }
        return new GenericApiResponse(200, "Success", "23097452893", bankCardInDB);
    }

    @DeleteMapping("/{id}")
    public GenericApiResponse deleteCard(@PathVariable String id) {
        this.bankCardService.delete(id);
        return new GenericApiResponse(200, "Success", "34265782");
    }

}
