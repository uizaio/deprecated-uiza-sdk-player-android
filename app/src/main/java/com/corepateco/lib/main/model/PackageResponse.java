package com.corepateco.lib.main.model;

import java.util.List;

/**
 * Created by HieuHD on 9/11/2016.
 */

public class PackageResponse {


    /**
     * id : 2177
     * title : 30-days
     * description : {}
     * logo : http://telebreeze.com/images/package_icon.jpg
     * period_type : day
     * period_number : 30
     * price : 30000.00
     * currency : 30000.00
     * tax_rate : 0.00000000
     */

    private List<Package> packages;

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }


}
