package com.javarush.cryptanalyzer.anokhov.jframeWindows;

import com.javarush.cryptanalyzer.anokhov.entity.Mode;

public interface Window {
   Mode getInformation ();
   void run (String mode);
}
