package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Kisi;
import com.example.demo.repository.KisiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class KisiService {

    public static KisiRepository kisiRepository;

    @Autowired
    public KisiService(KisiRepository kisiRepository ) {
        this.kisiRepository = kisiRepository;
    }

    public List<Kisi> tumKisileriGetir(){
        return kisiRepository.findAll();
    }

    public Kisi kisiEkle(Kisi kisi) {
        return kisiRepository.save(kisi);
    }

    public Optional<Kisi> idIleKisiGetir(Integer id) {
        return kisiRepository.findById(id);
    }

    public String idIleKisiSil(Integer id) {
        if(kisiRepository.findById(id) == null) {
            throw new IllegalStateException(id + " li kisi bulunamadi");
        }
        kisiRepository.deleteById(id);
        return id + " li kisi silindi";
    }

    public String tumKisileriSil(){
        if(kisiRepository.count() == 0){
            return "Veritabaninda kayitli kisi yok.";
        }else{
            kisiRepository.deleteAll();
            return "Tum Kisiler silindi." ;
        }
    }

    public Kisi idIleKisiGuncelle(Kisi guncelKisi) {
        kisiRepository.findById(guncelKisi.getId()).
                orElseThrow( () -> new IllegalStateException(guncelKisi.getId() + " li kisi bulunamdi"));
        return kisiRepository.save(guncelKisi);
    }

    public Kisi idIleKismiGuncelle(Integer id, Kisi yeniKisi) {
        Kisi eskiKisi = kisiRepository.findById(id).
                orElseThrow( () -> new IllegalStateException(id + " li kisi bulunamadi"));
        if(yeniKisi.getAd()!=null) {
            eskiKisi.setAd(yeniKisi.getAd());
        }
        if(yeniKisi.getSoyad()!=null) {
            eskiKisi.setSoyad(yeniKisi.getSoyad());
        }
        if(yeniKisi.getYas()!=0) {
            eskiKisi.setYas(yeniKisi.getYas());
        }
        return kisiRepository.save(eskiKisi);
    }


}