package io.jenkins.blueocean.api.pipeline;

import io.jenkins.blueocean.api.pipeline.model.Run;
import io.jenkins.blueocean.commons.JsonConverter;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * @author Vivek Pandey
 */
public class GetPipelineRunResponseTest {
    @Test
    public void serializeDeserialize(){
        GetPipelineRunResponse response = new GetPipelineRunResponse(
                new Run.Builder("23","test1","cloudbees")
                        .branch("master")
                        .commitId("ccaaa22")
                        .status(Run.Status.SUCCESSFUL)
                        .startTime(new Date())
                        .endTime(new Date(new Date().getTime() - 10000))
                        .durationInMillis(10000L)
                        .build());

        String json = JsonConverter.toJson(response);

        System.out.println("Converted from Java:\n"+json);

        GetPipelineRunResponse responseFromJson = JsonConverter.toJava(json,
                GetPipelineRunResponse.class);


        Run expected = response.run;
        Run actual = responseFromJson.run;

        Assert.assertEquals(expected.id, actual.id);
        Assert.assertEquals(expected.pipeline, actual.pipeline);
        Assert.assertEquals(expected.organization, actual.organization);
        Assert.assertEquals(expected.startTime.getTime(), actual.startTime.getTime());
        Assert.assertEquals(expected.endTime.getTime(), actual.endTime.getTime());
        Assert.assertEquals(expected.durationInMillis, actual.durationInMillis);

        System.out.println("Converted back from Json:\n"+JsonConverter.toJson(responseFromJson));
    }

}