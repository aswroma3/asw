package asw.efood.samplerestaurantcommandpublisher.domain;

import asw.efood.common.api.command.Command; 

public interface RestaurantCommandPublisher {

    public void publish(Command command); 

}
