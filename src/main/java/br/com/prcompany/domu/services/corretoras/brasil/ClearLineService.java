package br.com.prcompany.domu.services.corretoras.brasil;


import br.com.prcompany.domu.utils.DomuUtils;

public class ClearLineService extends SinacorLineService{

  @Override
  protected String getCorretora() {
    return DomuUtils.CLEAR_CORRETORA;
  }
}
