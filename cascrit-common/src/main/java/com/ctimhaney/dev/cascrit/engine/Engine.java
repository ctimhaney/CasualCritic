package com.ctimhaney.dev.cascrit.engine;

import java.util.ArrayList;

import com.ctimhaney.dev.cascrit.model.CritiqueObject;
import com.ctimhaney.dev.cascrit.model.CritiqueCollection;
import com.ctimhaney.dev.cascrit.model.CritiqueGroup;


public abstract class Engine {

  // convenience method
  public String processIntake(EngineIntake intake) {
    String result = "";
    if (intake.getEntity().equals("collection") || intake.getEntity().equals("col")) {
      CritiqueCollection ccollection = null;
      if (intake.getAction().equals("create") || intake.getAction().equals("cr")) {
        ccollection = createCollection(intake);
      } else if (intake.getAction().equals("read") || intake.getAction().equals("rd")) {
        ccollection = readCollection(intake);
      } else if (intake.getAction().equals("update") || intake.getAction().equals("up")) {
        ccollection = updateCollection(intake);
      } else if (intake.getAction().equals("delete") || intake.getAction().equals("dl")) {
        ccollection = deleteCollection(intake);
      }
      if (ccollection != null) {
        return ccollection.toString();
      }
    } else if (intake.getEntity().equals("group") || intake.getEntity().equals("grp")) {
      CritiqueGroup cgroup = null;
      if (intake.getAction().equals("create") || intake.getAction().equals("cr")) {
        cgroup = createGroup(intake);
      } else if (intake.getAction().equals("read") || intake.getAction().equals("rd")) {
        String returnme = "";
        for (CritiqueGroup g: readGroup(intake)) {
          if (g != null) {
            returnme += g.toString() + "\n";
          }
        }
        return returnme;
      } else if (intake.getAction().equals("update") || intake.getAction().equals("up")) {
        cgroup = updateGroup(intake);
      } else if (intake.getAction().equals("delete") || intake.getAction().equals("dl")) {
        cgroup = deleteGroup(intake);
      }
      if (cgroup != null) {
        return cgroup.toString();
      }
    }
    return null;
  }

  // Collection helper methods
  public abstract CritiqueCollection createCollection(EngineIntake intake);
  public abstract CritiqueCollection readCollection(EngineIntake intake);
  public abstract CritiqueCollection updateCollection(EngineIntake intake);
  public abstract CritiqueCollection deleteCollection(EngineIntake intake);

  // Group helper methods
  public abstract CritiqueGroup createGroup(EngineIntake intake);
  public abstract ArrayList<CritiqueGroup> readGroup(EngineIntake intake);
  public abstract CritiqueGroup updateGroup(EngineIntake intake);
  public abstract CritiqueGroup deleteGroup(EngineIntake intake);
}
