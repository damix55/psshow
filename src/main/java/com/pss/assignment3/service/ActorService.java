package com.pss.assignment3.service;

import com.pss.assignment3.dao.ActorDao;
import com.pss.assignment3.exception.ResourceNotFoundException;
import com.pss.assignment3.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ActorService {

    private final ActorDao actorDao;

    @Autowired
    public ActorService(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    public List<Actor> getAllActors() {
        return actorDao.findAll();
    }

    public Actor getActorById(Long id) throws ResourceNotFoundException {

        return actorDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actor ID not found:: " + id));
    }

    public Actor addActor(Actor actor) {

        Actor marriedToActor = actor.getMarriedTo();

        if (marriedToActor != null) {
            Actor marriedToMarriedToActor = marriedToActor.getMarriedTo();
            if (marriedToMarriedToActor != null) {
                marriedToMarriedToActor.setMarriedTo(null);
            }
            marriedToActor.setMarriedTo(actor);
        }

        actor.setMarriedTo(marriedToActor);

        return actorDao.save(actor);
    }

    public void deleteActorById(Long id) throws ResourceNotFoundException {

        Actor actor = actorDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actor ID not found:: " + id));

        Actor marriedToActor = actor.getMarriedTo();

        if (marriedToActor != null) {
            marriedToActor.setMarriedTo(null);
            actorDao.save(marriedToActor);
        }

        actor.setMarriedTo(null);

        actorDao.delete(actor);
    }

    public void updateActor(Long id, Actor newActor) throws ResourceNotFoundException {

        Actor actor = actorDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actor ID not found:: " + id));

        actor.setName(newActor.getName());
        actor.setSurname(newActor.getSurname());
        actor.setKnownAs(newActor.getKnownAs());
        actor.setGender(newActor.getGender());
        actor.setBirthday(newActor.getBirthday());
        actor.setPicture(newActor.getPicture());
        Actor marriedToActor = newActor.getMarriedTo();

        if (marriedToActor != null) {
            Actor marriedToMarriedToActor = marriedToActor.getMarriedTo();
            if (marriedToMarriedToActor != null) {
                marriedToMarriedToActor.setMarriedTo(null);
            }
            marriedToActor.setMarriedTo(actor);
        }

        Actor marriedToActorOld = actor.getMarriedTo();
        if (marriedToActorOld != null) {
            marriedToActorOld.setMarriedTo(null);
        }
        actor.setMarriedTo(marriedToActor);

        actorDao.save(actor);
    }

    public List<Actor> searchActors(String query) {
        List<Actor> results = new ArrayList<>();
        query = query.toLowerCase();

        for (Actor actor : getAllActors()) {
            String name = actor.getName().toLowerCase();
            String surname = actor.getSurname().toLowerCase();
            String knownAs = actor.getKnownAs().toLowerCase();

            if (name.contains(query)) {
                results.add(actor);
                continue;
            }
            if (surname.toLowerCase().contains(query)) {
                results.add(actor);
                continue;
            }
            if ((name + " " + surname).contains(query)) {
                results.add(actor);
                continue;
            }
            if ((surname + " " + name).contains(query)) {
                results.add(actor);
                continue;
            }
            if (knownAs.contains(query)) {
                results.add(actor);
            }
        }

        return results;
    }

}
