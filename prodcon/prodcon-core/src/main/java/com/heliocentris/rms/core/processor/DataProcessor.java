package com.heliocentris.rms.core.processor;

import javax.ejb.Remote;

import com.heliocentris.rms.core.data.Header;

@Remote
public interface DataProcessor
{
   /**
    * Called from LifesignalProcessor, if the <code>Header</code> is the <code>LifeSignal</code>.
    * 
    * @param header the header of the <code>Data</code>.
    */
   void proccess(Header header);
   
}
