/**
 * FachadaWSSGS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.gov.bcb.www3.wssgs.services.FachadaWSSGS;

public interface FachadaWSSGS extends java.rmi.Remote {
    public br.gov.bcb.pec.sgs.casosdeuso.ws.comum.WSSerieVO[] getValoresSeriesVO(long[] in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException;
    public br.gov.bcb.pec.sgs.casosdeuso.ws.comum.WSSerieVO getUltimosValoresSerieVO(long in0, long in1) throws java.rmi.RemoteException;
    public java.lang.String getValoresSeriesXML(long[] in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException;
    public br.gov.bcb.pec.sgs.casosdeuso.ws.comum.WSSerieVO getUltimoValorVO(long in0) throws java.rmi.RemoteException;
    public java.lang.String getUltimoValorXML(long in0) throws java.rmi.RemoteException;
    public java.math.BigDecimal getValor(long in0, java.lang.String in1) throws java.rmi.RemoteException;
    public java.math.BigDecimal getValorEspecial(long in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException;
}
