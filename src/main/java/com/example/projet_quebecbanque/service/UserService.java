package com.example.projet_quebecbanque.service;

import com.example.projet_quebecbanque.controller.exception.UserNotFoundException;
import com.example.projet_quebecbanque.entities.User;
import com.example.projet_quebecbanque.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Name : UserService
 * Permet d'utiliser les méthode du crud pour modifier les informations
 *
 * @author Francis Lafontaine
 * @version V1
 * @since 6/octobre/2022
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Name :  lisAll
     * Permet de lister tout les user
     *
     * @return une list de user
     */
    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    /**
     * Name : get
     * Permet d'aller chercher tout les informations d'un user
     *
     * @param id du user rechercher
     * @return un objet de type user
     * @throws UserNotFoundException si on ne trouve pas le user
     */
    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = userRepository.findById(Long.valueOf(id));

        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Aucun user trouvé avec ce Id");
    }

    /**
     * Name : delete
     * Permet de supprimer un user dans la base de donnée
     *
     * @param id du user a supprimer
     * @throws UserNotFoundException si on ne trouve pas le user
     */
    public void delete(Integer id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(Long.valueOf(id));

        if (userOptional.isPresent()) {
            userRepository.deleteById(Long.valueOf(id));
        } else {
            throw new UserNotFoundException("Aucun user n'a été trouvé");
        }
    }

    /**
     * Name : save
     * permet de sauvegarder un user dans la base de donnée
     *
     * @param user qui sera sauvegarder
     */
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * Name : findByUsername
     * Permet de trouver un user par son username
     *
     * @param username du user
     * @return un objet de type user
     * @throws UserNotFoundException si on ne trouve pas de user
     */
    public User finByUsername(String username) throws UserNotFoundException {
        User user = userRepository.findUserByUserName(username);
        return user;
    }

    /**
     * Name : updateUser
     * Permet de metre a jour le username d'un user
     *
     * @param user le nuser qui seras mise a jour
     */
    @Transactional
    public void updateUser(User user) {
        userRepository
                .findById(user.getIdUser()) // returns Optional<User>
                .ifPresent(user1 -> {
                    user1.setUsername(user.getUsername());
                    userRepository.save(user1);
                });
    }


}
