package fr.slitherlink.save.adventureMode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 12/04/2023
 */
@XmlRootElement
public class AdventureModeSave {


    private List<Integer> finishedLevelIdList;

    public AdventureModeSave() {
        finishedLevelIdList = new ArrayList<>();
    }

    public List<Integer> getFinishedLevelIdList() {
        return finishedLevelIdList;
    }

    @XmlElement(name = "LevelId")
    public void setFinishedLevelIdList(List<Integer> finishedLevelIdList) {
        this.finishedLevelIdList = finishedLevelIdList;
    }
}
