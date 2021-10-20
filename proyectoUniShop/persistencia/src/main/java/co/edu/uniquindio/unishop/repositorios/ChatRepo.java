package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Integer> {
}
