package br.gov.bcb.www3.wssgs.services.FachadaWSSGS;

public class FachadaWSSGSProxy implements br.gov.bcb.www3.wssgs.services.FachadaWSSGS.FachadaWSSGS {
  private String _endpoint = null;
  private br.gov.bcb.www3.wssgs.services.FachadaWSSGS.FachadaWSSGS fachadaWSSGS = null;
  
  public FachadaWSSGSProxy() {
    _initFachadaWSSGSProxy();
  }
  
  public FachadaWSSGSProxy(String endpoint) {
    _endpoint = endpoint;
    _initFachadaWSSGSProxy();
  }
  
  private void _initFachadaWSSGSProxy() {
    try {
      fachadaWSSGS = (new br.gov.bcb.www3.wssgs.services.FachadaWSSGS.FachadaWSSGSServiceLocator()).getFachadaWSSGS();
      if (fachadaWSSGS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)fachadaWSSGS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)fachadaWSSGS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (fachadaWSSGS != null)
      ((javax.xml.rpc.Stub)fachadaWSSGS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.gov.bcb.www3.wssgs.services.FachadaWSSGS.FachadaWSSGS getFachadaWSSGS() {
    if (fachadaWSSGS == null)
      _initFachadaWSSGSProxy();
    return fachadaWSSGS;
  }
  
  public br.gov.bcb.pec.sgs.casosdeuso.ws.comum.WSSerieVO[] getValoresSeriesVO(long[] in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException{
    if (fachadaWSSGS == null)
      _initFachadaWSSGSProxy();
    return fachadaWSSGS.getValoresSeriesVO(in0, in1, in2);
  }
  
  public br.gov.bcb.pec.sgs.casosdeuso.ws.comum.WSSerieVO getUltimosValoresSerieVO(long in0, long in1) throws java.rmi.RemoteException{
    if (fachadaWSSGS == null)
      _initFachadaWSSGSProxy();
    return fachadaWSSGS.getUltimosValoresSerieVO(in0, in1);
  }
  
  public java.lang.String getValoresSeriesXML(long[] in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException{
    if (fachadaWSSGS == null)
      _initFachadaWSSGSProxy();
    return fachadaWSSGS.getValoresSeriesXML(in0, in1, in2);
  }
  
  public br.gov.bcb.pec.sgs.casosdeuso.ws.comum.WSSerieVO getUltimoValorVO(long in0) throws java.rmi.RemoteException{
    if (fachadaWSSGS == null)
      _initFachadaWSSGSProxy();
    return fachadaWSSGS.getUltimoValorVO(in0);
  }
  
  public java.lang.String getUltimoValorXML(long in0) throws java.rmi.RemoteException{
    if (fachadaWSSGS == null)
      _initFachadaWSSGSProxy();
    return fachadaWSSGS.getUltimoValorXML(in0);
  }
  
  public java.math.BigDecimal getValor(long in0, java.lang.String in1) throws java.rmi.RemoteException{
    if (fachadaWSSGS == null)
      _initFachadaWSSGSProxy();
    return fachadaWSSGS.getValor(in0, in1);
  }
  
  public java.math.BigDecimal getValorEspecial(long in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException{
    if (fachadaWSSGS == null)
      _initFachadaWSSGSProxy();
    return fachadaWSSGS.getValorEspecial(in0, in1, in2);
  }
  
  
}