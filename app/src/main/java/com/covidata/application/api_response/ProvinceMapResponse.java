package com.covidata.application.api_response;

import com.covidata.application.model.ProvinceMap;

import java.util.List;

public class ProvinceMapResponse {
    public String last_date;
    public int current_data, missing_data, tanpa_provinsi;
    public List<ProvinceMap> list_data;
}
