import pygame

# Initialize Pygame
pygame.init()

# Set the window dimensions
window_width = 2500
window_height = 2000

# Create the window
screen = pygame.display.set_mode((window_width, window_height))

# Set the title of the window
pygame.display.set_caption("Enigma")

# Define colors
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
GRAY = (128, 128, 128)

# Define font
font = pygame.font.Font(None, 52)

# Define button
button_text = font.render("Machine Settings", True, BLACK)
button_rect = button_text.get_rect()
button_rect.topleft = (40, 40)

button2_text = font.render("Back", True, BLACK)
button2_rect = button_text.get_rect()
button2_rect.topleft = (40, 40)

# Define state
state = "menu"

# Define menu
menu = pygame.Surface((window_width, window_height))
menu.fill(GRAY)

# Game loop
running = True
while running:

    # Handle events
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        elif event.type == pygame.MOUSEBUTTONDOWN and event.button == 1:
            if button_rect.collidepoint(event.pos):
                state = "settings"
            elif button2_rect.collidepoint(event.pos):
                state = "menu"

    # Update the screen
    if state == "menu":
        screen.fill(WHITE)
        screen.blit(button_text, button_rect)
    elif state == "settings":
        screen.blit(menu, (0, 0))
        screen.blit(button2_text, button2_rect)

    pygame.display.update()

# Quit Pygame
pygame.quit()
