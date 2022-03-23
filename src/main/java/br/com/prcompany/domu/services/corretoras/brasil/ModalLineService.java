package br.com.prcompany.domu.services.corretoras.brasil;


import br.com.prcompany.domu.utils.DomuUtils;

public class ModalLineService extends SinacorLineService{

  @Override
  protected String getCorretora() {
    return DomuUtils.MODAL_CORRETORA;
  }
}
