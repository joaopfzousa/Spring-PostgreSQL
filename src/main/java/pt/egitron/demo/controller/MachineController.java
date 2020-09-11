package pt.egitron.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pt.egitron.demo.service.MachineService;

@RestController
public class MachineController {

    private MachineService machineService;

    @Autowired
    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @DeleteMapping("/machines/{id}")
    public String deleteArticle(@PathVariable("id") Integer id){
        return machineService.deleteMachine(id);
    }

}
