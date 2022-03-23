package br.com.prcompany.domu.services.factory;

import br.com.prcompany.domu.services.ILineService;
import br.com.prcompany.domu.services.corretoras.brasil.ClearLineService;
import br.com.prcompany.domu.services.corretoras.brasil.ModalLineService;
import br.com.prcompany.domu.services.corretoras.us.AvenueLineService;
import br.com.prcompany.domu.services.enums.LineServiceEnum;

public final class LineServiceFactory {

  private LineServiceFactory() {}

  public static ILineService getFactory(LineServiceEnum lineServiceEnum) {
    switch (lineServiceEnum) {
      case CLEAR:
        return new ClearLineService();
      case MODAL:
        return new ModalLineService();
      case AVENUE:
        return new AvenueLineService();
    }

    throw new IllegalArgumentException("Argumento não reconhcido: " + lineServiceEnum);
  }
}
