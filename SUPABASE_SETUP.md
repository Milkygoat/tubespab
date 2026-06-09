# PlanMate - Supabase Setup Guide

## Supabase Configuration

Your Supabase URL and anon key are already configured in `app/build.gradle.kts`:

    SUPABASE_URL0115 = "https://jwrdrksijwphkgqmlvjt.supabase.co/"
    SUPABASE_KEY0115 = "eyJhbGciOiJIUzI1NiIs..."

## Database Setup

Run the following SQL in your Supabase SQL Editor (Dashboard > SQL Editor > New Query):

### Step 1: Drop existing tables (if any)

    DROP TABLE IF EXISTS public.tasks;
    DROP TABLE IF EXISTS public.events;

### Step 2: Create new tables

    CREATE TABLE public.tasks (
        id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
        username text NOT NULL,
        title text NOT NULL,
        description text,
        deadline timestamp,
        status boolean DEFAULT false,
        created_at timestamp DEFAULT now()
    );

    CREATE TABLE public.events (
        id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
        username text NOT NULL,
        title text NOT NULL,
        description text,
        location text,
        event_date timestamp,
        created_at timestamp DEFAULT now()
    );

### Step 3: Disable Row Level Security

    ALTER TABLE public.tasks DISABLE ROW LEVEL SECURITY;
    ALTER TABLE public.events DISABLE ROW LEVEL SECURITY;

## How It Works

- No login or registration is required
- The app uses SharedPreferences to store the username locally
- The username is used to filter tasks and events in the database
- All API calls use the Supabase anon key (no user tokens)

## Security Note

- This setup is suitable for educational/school projects
- For production apps, you would implement proper authentication and RLS policies
- Do not put your service_role (secret) key in the client app
