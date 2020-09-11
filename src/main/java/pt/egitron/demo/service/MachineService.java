package pt.egitron.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pt.egitron.demo.model.Article;
import pt.egitron.demo.model.Machine;
import pt.egitron.demo.repository.MachineRepo;

import java.util.Optional;

@Service
public class MachineService {

    private final MachineRepo machineRepo;

    @Autowired
    public MachineService(MachineRepo machineRepo) {
        this.machineRepo = machineRepo;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String deleteMachine(Integer id)
    {
       Machine m = this.findById(id);

       for (Article a : m.getArticles())
       {
           a.removeMachine(m);
       }

       machineRepo.deleteById(id);

       return "Remove Machine with id = " + id;
    }

    public Machine findById(Integer id)
    {
        Optional<Machine> machine_opt = machineRepo.findById(id);

        Machine m;
        if(machine_opt.isPresent()) m = machine_opt.get();
        else return null;

        return m;

    }
}
