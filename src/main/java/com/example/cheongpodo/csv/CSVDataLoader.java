package com.example.cheongpodo.csv;

import com.example.cheongpodo.domain.SpaceInfo;
import com.example.cheongpodo.repository.SpaceInfoRepository;
import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.io.Reader;

@Component
@RequiredArgsConstructor
public class CSVDataLoader implements CommandLineRunner {

    private final SpaceInfoRepository spaceInfoRepository;
    @Override
    public void run(String... args) throws Exception {
        try(Reader reader = new InputStreamReader(new ClassPathResource("csv/space.csv").getInputStream());
            CSVReader csvReader = new CSVReader(reader)){
            String[] line;
            while((line = csvReader.readNext())!=null){
                Long spaceId = Long.parseLong(line[0].replace("\uFEFF", "").trim());
                String spaceEmail = line[1].trim();
                SpaceInfo spaceInfo = new SpaceInfo();
                spaceInfo.setSpaceEmail(spaceEmail);
                spaceInfo.setSpaceId(spaceId);
                spaceInfoRepository.save(spaceInfo);
            }

        }
    }
}
