package com.moringaschool.adoptpet.network;

import com.moringaschool.adoptpet.models.PetSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PetfinderApi {
    @GET("animals")
    Call<PetSearchResponse> getPets(
            @Query("status") String find
    );
}