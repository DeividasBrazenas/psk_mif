package debr.rest;

import debr.entities.Player;
import debr.persistence.PlayersDAO;
import debr.rest.contracts.PlayerDTO;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/players")
public class PlayersController {
    @Inject
    @Getter
    @Setter
    private PlayersDAO playersDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Player player = playersDAO.findOne(id);
        if (player == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        PlayerDTO playerDto = new PlayerDTO();
        playerDto.setFirstName(player.getFirstName());
        playerDto.setLastName(player.getLastName());
        playerDto.setNickName(player.getNickName());

        return Response.ok(playerDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response add(
            PlayerDTO playerData) {

        Player newPlayer = new Player();
        newPlayer.setFirstName(playerData.getFirstName());
        newPlayer.setLastName(playerData.getLastName());
        newPlayer.setNickName(playerData.getNickName());

        playersDAO.persist(newPlayer);

        return Response.ok().build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer playerId,
            PlayerDTO playerData) {
        try {
            Player existingPlayer = playersDAO.findOne(playerId);
            if (existingPlayer == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            existingPlayer.setFirstName(playerData.getFirstName());
            existingPlayer.setLastName(playerData.getLastName());
            existingPlayer.setNickName(playerData.getNickName());

            playersDAO.update(existingPlayer);

            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
